package com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCustomerRequestDTO(
    @NotNull(message = "El ID es obligatorio para actualizar un cliente")
    UUID id,

    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    String firstName,

    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    String lastName,

    @Pattern(regexp = "^\\d{9}$", message = "El NIT debe contener exactamente 9 dígitos numéricos")
    String nit,

    @Pattern(regexp = "^\\d{8}$", message = "El número de teléfono debe contener exactamente 8 dígitos numéricos")
    String phoneNumber
) {}
