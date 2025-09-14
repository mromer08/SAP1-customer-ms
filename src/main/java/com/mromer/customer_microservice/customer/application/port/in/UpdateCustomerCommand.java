package com.mromer.customer_microservice.customer.application.port.in;

import java.util.UUID;

public record UpdateCustomerCommand(
        UUID id,
        String firstName,
        String lastName,
        String nit,
        String phoneNumber
) {}
