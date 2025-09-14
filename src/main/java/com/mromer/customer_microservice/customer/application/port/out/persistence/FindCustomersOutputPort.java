package com.mromer.customer_microservice.customer.application.port.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mromer.customer_microservice.customer.application.port.in.FindCustomersCommand;
import com.mromer.customer_microservice.customer.domain.Customer;

public interface FindCustomersOutputPort {
    Page<Customer> findAll(FindCustomersCommand command, Pageable pageable);
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByNit(String nit);
    boolean existsById(UUID id);
    boolean existsByNIT(String nit);
    boolean existsByNITAndIdNot(String nit, UUID id);
}