package com.atuka.managecustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.atuka.managecustomer", "com.atuka.amqp"})
@EnableFeignClients(basePackages = "com.atuka.clients")
public class ManageCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCustomerApplication.class, args);
    }

}
