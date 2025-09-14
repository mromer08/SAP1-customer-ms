package com.mromer.customer_microservice.customer.domain;

import java.util.Objects;

public record PhoneNumber(String value) {
    public PhoneNumber {
        Objects.requireNonNull(value, "El numero de telefono no puede ser nulo");
        if (!value.matches("\\d{8}")) {
            throw new IllegalArgumentException("El numero de telefono debe tener exactamente 8 digitos");
        }
    }
}

