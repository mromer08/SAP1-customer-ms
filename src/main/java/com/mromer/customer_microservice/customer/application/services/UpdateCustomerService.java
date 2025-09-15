package com.mromer.customer_microservice.customer.application.services;

import org.springframework.transaction.annotation.Transactional;

import com.mromer.customer_microservice.customer.application.exceptions.CustomerNotFoundException;
import com.mromer.customer_microservice.customer.application.exceptions.DuplicateNITException;
import com.mromer.customer_microservice.customer.application.mappers.CustomerMapper;
import com.mromer.customer_microservice.customer.application.port.in.*;
import com.mromer.customer_microservice.customer.application.port.out.persistence.*;
import com.mromer.customer_microservice.customer.domain.Customer;

@Transactional(rollbackFor = Exception.class)
public class UpdateCustomerService implements UpdateCustomerInputPort {

    private final StoreCustomerOutputPort storingCustomer;
    private final FindCustomersOutputPort findingCustomer;

    public UpdateCustomerService(StoreCustomerOutputPort storingCustomer,
            FindCustomersOutputPort findingCustomer) {
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
