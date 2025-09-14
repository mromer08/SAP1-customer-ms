package com.mromer.customer_microservice.customer.application.port.out.persistence;

import com.mromer.customer_microservice.customer.domain.Customer;

public interface StoringCustomerOutputPort {
    Customer save(Customer customer);
}
