package com.mromer.customer_microservice.customer.application.services;

import java.util.*;

import org.springframework.data.domain.Pageable;

import com.mromer.customer_microservice.common.application.PagedResponseDTO;
import com.mromer.customer_microservice.customer.application.port.in.FindCustomersCommand;
import com.mromer.customer_microservice.customer.application.port.in.FindingCustomerInputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.FindingCustomersOutputPort;
import com.mromer.customer_microservice.customer.domain.Customer;

public class FindCustomerService implements FindingCustomerInputPort {

    private final FindingCustomersOutputPort findingCustomersOutputPort;

    public FindCustomerService(FindingCustomersOutputPort findingCustomersOutputPort) {
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
    public PagedResponseDTO<Customer> findAll(FindCustomersCommand command, Pageable pageable) {
        return findingCustomersOutputPort.findAll(command, pageable);
    }
}
