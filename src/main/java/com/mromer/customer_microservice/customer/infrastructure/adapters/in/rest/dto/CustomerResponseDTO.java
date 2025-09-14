package com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto;

import java.util.UUID;

public record CustomerResponseDTO(
    UUID id,
    String firstName,
    String lastName,
    String nit,
    String phoneNumber,
    Boolean active
) {}
