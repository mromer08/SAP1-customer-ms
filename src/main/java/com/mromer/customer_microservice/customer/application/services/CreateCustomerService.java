package com.mromer.customer_microservice.customer.application.services;

import com.mromer.customer_microservice.customer.application.exceptions.DuplicateNITException;
import com.mromer.customer_microservice.customer.application.mappers.CustomerMapper;
import com.mromer.customer_microservice.customer.application.port.in.CreateCustomerCommand;
import com.mromer.customer_microservice.customer.application.port.in.CreatingCustomerInputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.FindingCustomersOutputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.StoringCustomerOutputPort;
import com.mromer.customer_microservice.customer.domain.Customer;

public class CreateCustomerService implements CreatingCustomerInputPort {

    private final StoringCustomerOutputPort storingCustomerOutputPort;
    private final FindingCustomersOutputPort findingCustomersOutputPort;

    public CreateCustomerService(StoringCustomerOutputPort storingCustomerOutputPort,
            FindingCustomersOutputPort findingCustomersOutputPort) {
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
