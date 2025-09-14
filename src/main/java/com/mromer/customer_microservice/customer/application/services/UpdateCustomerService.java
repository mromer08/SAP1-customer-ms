package com.mromer.customer_microservice.customer.application.services;

import com.mromer.customer_microservice.customer.application.mappers.CustomerMapper;
import com.mromer.customer_microservice.customer.application.port.in.UpdateCustomerCommand;
import com.mromer.customer_microservice.customer.application.port.in.UpdateCustomerInputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.FindingCustomersOutputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.StoringCustomerOutputPort;
import com.mromer.customer_microservice.customer.domain.Customer;
import com.mromer.customer_microservice.customer.domain.exceptions.CustomerNotFoundException;
import com.mromer.customer_microservice.customer.domain.exceptions.DuplicateNITException;

public class UpdateCustomerService implements UpdateCustomerInputPort {

    private final StoringCustomerOutputPort storingCustomer;
    private final FindingCustomersOutputPort findingCustomer;

    public UpdateCustomerService(StoringCustomerOutputPort storingCustomer,
            FindingCustomersOutputPort findingCustomer) {
        this.storingCustomer = storingCustomer;
        this.findingCustomer = findingCustomer;
    }

    @Override
    public Customer updateCustomer(UpdateCustomerCommand command) {
        Customer existing = findingCustomer.findById(command.id())
                .orElseThrow(() -> new CustomerNotFoundException(command.id().toString()));

        if (command.nit() != null && findingCustomer.existsByNITAndIdNot(command.nit(), existing.getId().value())) {
            throw new DuplicateNITException(command.nit());
        }

        Customer updated = CustomerMapper.updateCustomer(existing, command);

        return storingCustomer.save(updated);
    }
}
