package com.atuka.eurekaserver.managecustomer.Controller;

import com.atuka.eurekaserver.managecustomer.Model.Customer;
import com.atuka.eurekaserver.managecustomer.Request.CustomerRequest;
import com.atuka.eurekaserver.managecustomer.Service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
@AllArgsConstructor

public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("New Customer Registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable Integer customerId) {
        return customerService.deleteCustomerById(customerId);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(Customer customer, Integer customerId) {
        return customerService.updateCustomerInformation(customer, customerId);
    }
}
