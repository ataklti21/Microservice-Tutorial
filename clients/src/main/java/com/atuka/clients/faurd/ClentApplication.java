package com.atuka.clients.faurd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClentApplication.class,args);
        System.out.println("this is the Client");
    }
}
