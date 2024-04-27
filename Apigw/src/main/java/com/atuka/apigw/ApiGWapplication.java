package com.atuka.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiGWapplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGWapplication.class,args);
        System.out.println("Gateway Started");
    }
}
