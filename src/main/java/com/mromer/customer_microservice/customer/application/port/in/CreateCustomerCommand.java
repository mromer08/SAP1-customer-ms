package com.mromer.customer_microservice.customer.application.port.in;

public record CreateCustomerCommand(
        String firstName,
        String lastName,
        String nit,
        String phoneNumber
) {}
