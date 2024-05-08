package com.atuka.managecustomer.Service;



import com.atuka.Rabbitamqp.RabbitMaMessageProducer;
import com.atuka.clients.faurd.FraudClient;

import com.atuka.clients.faurd.Request.NotificationRequest;
import com.atuka.clients.faurd.Respose.FraudCheckResponse;
import com.atuka.managecustomer.Model.Customer;
import com.atuka.managecustomer.Repository.CustomerRepository;
import com.atuka.managecustomer.Request.CustomerRequest;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class CustomerService {
    private final CustomerRepository repository;
    private final FraudClient fraudClient;
    private final RabbitMaMessageProducer rabbitMaMessageProducer;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).emailAddress(request.emailAddress()).build();
        //todo:check email is valid
        //todo:check if email not taken
        //todo:check if fraudster

        //persisting  customer
        repository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getCustomerId());
        //send notification
        //Making this sync i.e add queue
        NotificationRequest notificationRequest = new NotificationRequest(customer.getCustomerId(),
                customer.getEmailAddress(),
                String.format("Hi %S welcome to com.Atuka",
                        customer.getFirstName()));

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is fraudulent ");
        }

        rabbitMaMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }

    /**
     * Getting Customer using customer Id
     * @param id customer id
     * @return a customer with the specified Id
     */
    public ResponseEntity<Customer> getCustomerById(Integer id) {
        Optional<Customer> customerOptional = repository.findById(id);
        return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes customer using customer id
     * @param customerId customer id
     * @return HttpStatus ok if there is no error but return notfound exception
     */
    public ResponseEntity<HttpStatus> deleteCustomerById(Integer customerId) {
        Optional<Customer> customer = repository.findById(customerId);
        if (customer.isPresent()) {
            repository.deleteById(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update existing customer using customer id

     * takes two parameters Customer object and customer id
     * @param customerData
     * @param customerId
     * @return  if customer exits the method returns the updated
     *      customer
     *      else returns Resource not found response
     */
    public ResponseEntity<Customer> updateCustomerInformation(Customer customerData, Integer customerId) {
        Optional<Customer> customer = repository.findById(customerId);
        if (customer.isPresent()) {
            Customer _customer = customer.get();
            _customer.setFirstName(customerData.getFirstName());
            _customer.setEmailAddress(customerData.getEmailAddress());
            _customer.setLastName(customerData.getLastName());
            return  new ResponseEntity<>(repository.save(_customer),HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
