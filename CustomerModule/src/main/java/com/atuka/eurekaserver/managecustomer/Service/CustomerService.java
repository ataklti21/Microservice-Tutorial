package com.atuka.eurekaserver.managecustomer.Service;

import com.atuka.clients.faurd.FraudClient;
import com.atuka.clients.faurd.Respose.FraudCheckResponse;
import com.atuka.eurekaserver.managecustomer.Model.Customer;
import com.atuka.eurekaserver.managecustomer.Repository.CustomerRepository;
import com.atuka.eurekaserver.managecustomer.Request.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor

public class CustomerService {
    private final CustomerRepository repository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .emailAddress(request.emailAddress()).build();
        //todo:check email is valid
        //todo:check if email not taken
        //todo:check if fraudster

        //store customer db
        repository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getCustomerId());
        //todo send notification

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is fraudulent ");
        }
    }
}
