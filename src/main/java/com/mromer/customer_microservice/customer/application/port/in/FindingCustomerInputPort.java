package com.mromer.customer_microservice.customer.application.port.in;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.mromer.customer_microservice.common.application.PagedResponseDTO;
import com.mromer.customer_microservice.customer.domain.Customer;

public interface FindingCustomerInputPort {
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByNIT(String nit);
    PagedResponseDTO<Customer> findAll(FindCustomersCommand command, Pageable pageable);
}
