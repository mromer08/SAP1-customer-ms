package com.mromer.customer_microservice.customer.application.port.in;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mromer.customer_microservice.customer.domain.Customer;

public interface FindCustomerInputPort {
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByNIT(String nit);
    Page<Customer> findAll(FindCustomersCommand command, Pageable pageable);
}
