package com.mromer.customer_microservice.customer.infrastructure.adapters.out.persistence;

import com.mromer.customer_microservice.customer.domain.Customer;
import com.mromer.customer_microservice.customer.domain.CustomerId;
import com.mromer.customer_microservice.customer.domain.NIT;
import com.mromer.customer_microservice.customer.domain.PhoneNumber;

public class CustomerEntityMapper {

    public static Customer toDomain(CustomerEntity entity) {
        Customer customer = new Customer(
                new CustomerId(entity.getId()),
                entity.getFirstName(),
                entity.getLastName(),
                new NIT(entity.getNit()),
                new PhoneNumber(entity.getPhoneNumber())
        );

        if (!entity.isActive()) {
            customer.deactivate();
        }

        return customer;
    }

    public static CustomerEntity toEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().value())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .nit(customer.getNit().value())
                .phoneNumber(customer.getPhoneNumber().value())
                .active(customer.isActive())
                .build();
    }
}
