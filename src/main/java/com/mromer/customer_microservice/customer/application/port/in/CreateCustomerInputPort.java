package com.mromer.customer_microservice.customer.application.port.in;

import com.mromer.customer_microservice.customer.domain.Customer;

public interface CreateCustomerInputPort {
    Customer createCustomer(CreateCustomerCommand command);
}
