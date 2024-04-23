package com.atuka.eurekaserver.managecustomer.Controller;

import com.atuka.eurekaserver.managecustomer.Request.CustomerRequest;
import com.atuka.eurekaserver.managecustomer.Service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
@AllArgsConstructor
public class CustomerController {
    private  final CustomerService customerService;
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("New Customer Registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
