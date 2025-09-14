package com.mromer.customer_microservice.customer.application.port.in;

public record FindCustomersCommand(
        String firstName,
        String lastName,
        String nit
) {}
