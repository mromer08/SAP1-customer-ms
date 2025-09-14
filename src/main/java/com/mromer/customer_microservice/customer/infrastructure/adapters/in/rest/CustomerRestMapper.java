package com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest;

import com.mromer.customer_microservice.customer.domain.Customer;
import com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto.CustomerResponseDTO;

public class CustomerRestMapper {

    public static CustomerResponseDTO toResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId().value(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getNit().value(),
                customer.getPhoneNumber().value(),
                customer.isActive()
        );
    }
}
