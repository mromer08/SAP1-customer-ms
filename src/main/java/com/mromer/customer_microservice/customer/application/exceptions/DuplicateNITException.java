package com.mromer.customer_microservice.customer.application.exceptions;

public class DuplicateNITException extends RuntimeException {
    public DuplicateNITException(String nit) {
        super("Un cliente con el NIT '" + nit + "' ya existe.");
    }
    
}
