package com.mromer.customer_microservice.customer.application.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    
    public CustomerNotFoundException(String id) {
        super("Cliente con el ID " + id + " no encontrado.");
    }
    
}
