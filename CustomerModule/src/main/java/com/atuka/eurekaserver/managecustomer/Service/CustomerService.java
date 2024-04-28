package com.atuka.eurekaserver.managecustomer.Service;

import com.atuka.clients.faurd.FraudClient;
import com.atuka.clients.faurd.NotificationClient;
import com.atuka.clients.faurd.Request.NotificationRequest;
import com.atuka.clients.faurd.Respose.FraudCheckResponse;
import com.atuka.eurekaserver.managecustomer.Model.Customer;
import com.atuka.eurekaserver.managecustomer.Repository.CustomerRepository;
import com.atuka.eurekaserver.managecustomer.Request.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class CustomerService {
    private final CustomerRepository repository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).emailAddress(request.emailAddress()).build();
        //todo:check email is valid
        //todo:check if email not taken
        //todo:check if fraudster

        //persisting  customer
        repository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getCustomerId());
        //send notification
        //todo:Make this sync i.e add queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getCustomerId(),
                        customer.getEmailAddress(),
                        String.format("Hi %S welcome to com.Atuka", customer.getFirstName())));
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is fraudulent ");
        }
    }
}
