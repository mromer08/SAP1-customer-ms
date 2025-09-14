package com.mromer.customer_microservice.customer.domain;

import java.util.Objects;

public class Customer {

    private final CustomerId id;
    private final String firstName;
    private final String lastName;
    private final NIT nit;
    private final PhoneNumber phoneNumber;
    private boolean active;

    public Customer(CustomerId id,
                    String firstName,
                    String lastName,
                    NIT nit,
                    PhoneNumber phoneNumber) {
        this.id = Objects.requireNonNull(id);
        this.firstName = validateName(firstName);
        this.lastName = validateName(lastName);
        this.nit = Objects.requireNonNull(nit);
        this.phoneNumber = Objects.requireNonNull(phoneNumber);
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser vacio");
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

    public boolean isActive() {
        return active;
    }
}
