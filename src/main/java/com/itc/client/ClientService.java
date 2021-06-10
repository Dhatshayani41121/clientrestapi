package com.itc.client;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {

    private final List<Client> clientList = new ArrayList<>(Arrays.asList(

            new Client("Rohit", "Sharma", "8000000001",
                    "001", "Mumbai"),
            new Client("Virat", "Kohli", "8000000002",
                    "002", "Delhi"),
            new Client("Bhuvaneshwar", "Kumar", "8000000003",
                    "003", "Haryana"),
            new Client("Jasprit", "Bumrah", "8000000004",
                    "004", "Maharashtra"),
            new Client("Mahendra singh", "Dhoni", "8000000005",
                    "005", "Ranchi")
    ));

    public List<Client> getAllClientList() {
        return clientList;
    }

    public Client getClient(String parameter) {
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getFirstName().equals(parameter) ||
                    clientList.get(i).getMobileNumber().equals(parameter) ||
                    clientList.get(i).getIdNumber().equals(parameter)) {
                return clientList.get(i);
            }
        }
        return null;
    }

    public Client addClient(Client client) {
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getIdNumber().equals(client.getIdNumber()) ||
                    clientList.get(i).getMobileNumber().equals(client.getMobileNumber()))
                return null;
        }
        clientList.add(client);
        return client;
    }

    public Client updateClient(Client client, String firstName) {
        int counter = 0;
        int index = 0;
        for (Client client1 : clientList) {
            if (client1.getFirstName().equals(client.getFirstName())) {
                index = counter;
            } else if (client1.getMobileNumber().equals(client.getMobileNumber()) ||
                    client1.getIdNumber().equals(client.getIdNumber())) {
                return null;
            }
            counter++;
        }
        clientList.set(index, client);
        return client;
    }

    public void deleteClient(String firstName) {
        clientList.removeIf(client -> client.getFirstName().equals(firstName));
    }
}