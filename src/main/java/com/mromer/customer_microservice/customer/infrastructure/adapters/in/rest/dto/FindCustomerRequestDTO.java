package com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto;

public record FindCustomerRequestDTO(
    String firstName,
    String lastName,
    String nit,
    Boolean active
) {}
