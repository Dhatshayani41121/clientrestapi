package com.itc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext c = SpringApplication.run(ClientApplication.class, args);

        System.out.println("Welcome to Spring Boot");
    }

}
