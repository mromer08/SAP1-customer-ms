package com.mromer.customer_microservice.customer.application.services;

import org.springframework.transaction.annotation.Transactional;

import com.mromer.customer_microservice.customer.application.exceptions.DuplicateNITException;
import com.mromer.customer_microservice.customer.application.mappers.CustomerMapper;
import com.mromer.customer_microservice.customer.application.port.in.*;
import com.mromer.customer_microservice.customer.application.port.out.persistence.*;
import com.mromer.customer_microservice.customer.domain.Customer;

@Transactional(rollbackFor = Exception.class)
public class CreateCustomerService implements CreateCustomerInputPort {

    private final StoreCustomerOutputPort storingCustomerOutputPort;
    private final FindCustomersOutputPort findingCustomersOutputPort;

    public CreateCustomerService(StoreCustomerOutputPort storingCustomerOutputPort,
            FindCustomersOutputPort findingCustomersOutputPort) {
        this.storingCustomerOutputPort = storingCustomerOutputPort;
        this.findingCustomersOutputPort = findingCustomersOutputPort;
    }

    @Override
    public Customer createCustomer(CreateCustomerCommand command) {
        if (findingCustomersOutputPort.existsByNIT(command.nit())) {
            throw new DuplicateNITException(command.nit());
        }

        Customer customer = CustomerMapper.toDomain(command);
        return storingCustomerOutputPort.save(customer);
    }
}
