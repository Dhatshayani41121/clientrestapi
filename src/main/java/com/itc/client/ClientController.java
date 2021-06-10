package com.itc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping("/clients")
    @GetMapping(value = "/clients")
    public List<Client> allClients() {
        return clientService.getAllClientList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients/{searchKey}")
    public ResponseEntity<Client> getClient(@PathVariable("searchKey") String searchKey) throws Exception {
        Client foundClient = clientService.getClient(searchKey);
        if (foundClient == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundClient);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clients")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        if (client.getIdNumber() == null || client.getFirstName() == null || client.getLastName() == null) {
            return ResponseEntity.badRequest().build();
        }

        Client createdClient = clientService.addClient(client);
        if (createdClient == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{firstName}")
                    .buildAndExpand(createdClient.getFirstName())
                    .toUri();
            return ResponseEntity.created(uri).body(createdClient);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/clients/{firstName}")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable("firstName")
            String firstName) {
        if (client.getIdNumber() == null || client.getFirstName() == null || client.getLastName() == null) {
            return ResponseEntity.badRequest().build();
        }

        Client updatedClient = clientService.updateClient(client, firstName);
        if (updatedClient == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(updatedClient);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clients/{firstName}")
    public ResponseEntity<Object> deleteClient(@PathVariable("firstName") String firstName) {
        clientService.deleteClient(firstName);
        return ResponseEntity.ok().build();
    }
}
