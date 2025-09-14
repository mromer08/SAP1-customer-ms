package com.mromer.customer_microservice.customer.application.services;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mromer.customer_microservice.customer.application.port.in.FindCustomerInputPort;
import com.mromer.customer_microservice.customer.application.port.in.FindCustomersCommand;
import com.mromer.customer_microservice.customer.application.port.out.persistence.FindCustomersOutputPort;
import com.mromer.customer_microservice.customer.domain.Customer;

public class FindCustomerService implements FindCustomerInputPort {

    private final FindCustomersOutputPort findingCustomersOutputPort;

    public FindCustomerService(FindCustomersOutputPort findingCustomersOutputPort) {
        this.findingCustomersOutputPort = findingCustomersOutputPort;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return findingCustomersOutputPort.findById(id);
    }

    @Override
    public Optional<Customer> findByNIT(String nit) {
        return findingCustomersOutputPort.findByNit(nit);
    }

    @Override
    public Page<Customer> findAll(FindCustomersCommand command, Pageable pageable) {
        return findingCustomersOutputPort.findAll(command, pageable);
    }
}
