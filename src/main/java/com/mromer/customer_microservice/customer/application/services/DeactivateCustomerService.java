package com.mromer.customer_microservice.customer.application.services;

import java.util.UUID;

import com.mromer.customer_microservice.customer.application.exceptions.CustomerNotFoundException;
import com.mromer.customer_microservice.customer.application.port.in.*;
import com.mromer.customer_microservice.customer.application.port.out.persistence.*;
import com.mromer.customer_microservice.customer.domain.Customer;

public class DeactivateCustomerService implements DeactivateCustomerInputPort {

    private final FindCustomersOutputPort findingCustomersOutputPort;
    private final StoreCustomerOutputPort storingCustomerOutputPort;

    public DeactivateCustomerService(FindCustomersOutputPort findingCustomersOutputPort,
                                     StoreCustomerOutputPort storingCustomerOutputPort) {
        this.findingCustomersOutputPort = findingCustomersOutputPort;
        this.storingCustomerOutputPort = storingCustomerOutputPort;
    }

    @Override
    public void deactivateCustomer(UUID id) {
        Customer customer = findingCustomersOutputPort.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id.toString()));

        customer.deactivate();

        storingCustomerOutputPort.save(customer);
    }
}
