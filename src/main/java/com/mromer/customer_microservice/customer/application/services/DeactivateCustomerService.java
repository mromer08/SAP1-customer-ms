package com.mromer.customer_microservice.customer.application.services;

import java.util.UUID;

import com.mromer.customer_microservice.customer.application.port.in.DeactivatingCustomerInputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.FindingCustomersOutputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.StoringCustomerOutputPort;
import com.mromer.customer_microservice.customer.domain.Customer;
import com.mromer.customer_microservice.customer.domain.exceptions.CustomerNotFoundException;

public class DeactivateCustomerService implements DeactivatingCustomerInputPort {

    private final FindingCustomersOutputPort findingCustomersOutputPort;
    private final StoringCustomerOutputPort storingCustomerOutputPort;

    public DeactivateCustomerService(FindingCustomersOutputPort findingCustomersOutputPort,
                                     StoringCustomerOutputPort storingCustomerOutputPort) {
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
