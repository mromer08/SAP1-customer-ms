package com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequestDTO(
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    String firstName,
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    String lastName,
    
    @NotBlank(message = "El NIT es obligatorio")
    @Pattern(regexp = "^\\d{9}$", message = "El NIT debe contener exactamente 9 dígitos numéricos")
    String nit,
        
    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^\\d{8}$", message = "El número de teléfono debe contener exactamente 8 dígitos numéricos")
    String phoneNumber
) {}