package com.mromer.customer_microservice.customer.domain;

import java.util.Objects;

public record PhoneNumber(String value) {
    public PhoneNumber {
        Objects.requireNonNull(value, "Phone number cannot be null");
        if (!value.matches("\\d{8}")) {
            throw new IllegalArgumentException("Phone number must be exactly 8 digits");
        }
    }
}

