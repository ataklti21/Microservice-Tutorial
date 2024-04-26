package com.atuka.eurekaserver.managecustomer.Request;

public record CustomerRequest(String firstName,
                              String lastName,
                              String emailAddress) {
}
