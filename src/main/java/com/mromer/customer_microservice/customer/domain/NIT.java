package com.mromer.customer_microservice.customer.domain;

import java.util.Objects;

public record NIT(String value) {
    public NIT {
        Objects.requireNonNull(value, "NIT cannot be null");
        if (!value.matches("\\d{9}")) {
            throw new IllegalArgumentException("NIT must be exactly 9 digits");
        }
    }
}


