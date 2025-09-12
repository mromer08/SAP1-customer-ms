package com.mromer.customer_microservice.customer.domain;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(UUID value) {
    public CustomerId {
        Objects.requireNonNull(value, "CustomerId cannot be null");
    }

    public static CustomerId generate() {
        return new CustomerId(UUID.randomUUID());
    }
}

