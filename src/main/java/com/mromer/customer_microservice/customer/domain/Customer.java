package com.mromer.customer_microservice.customer.domain;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private final CustomerId id;
    private final String firstName;
    private final String lastName;
    private final NIT nit;
    private final PhoneNumber phoneNumber;
    // Link to user account
    // private final UUID userId;

    public Customer(CustomerId id,
                    String firstName,
                    String lastName,
                    NIT nit,
                    PhoneNumber phoneNumber,
                    UUID userId) {
        this.id = Objects.requireNonNull(id);
        this.firstName = validateName(firstName);
        this.lastName = validateName(lastName);
        this.nit = Objects.requireNonNull(nit);
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        // this.userId = userId;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return name.trim();
    }

    public CustomerId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public NIT getNit() {
        return nit;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    // public UUID getUserId() {
    //     return userId;
    // }
}
