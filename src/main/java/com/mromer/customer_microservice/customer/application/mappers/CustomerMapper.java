package com.mromer.customer_microservice.customer.application.mappers;

import com.mromer.customer_microservice.customer.application.port.in.CreateCustomerCommand;
import com.mromer.customer_microservice.customer.application.port.in.UpdateCustomerCommand;
import com.mromer.customer_microservice.customer.domain.Customer;
import com.mromer.customer_microservice.customer.domain.CustomerId;
import com.mromer.customer_microservice.customer.domain.NIT;
import com.mromer.customer_microservice.customer.domain.PhoneNumber;

public class CustomerMapper {
    private CustomerMapper() {
        // Private constructor to prevent instantiation
    }

    public static Customer toDomain(CreateCustomerCommand command) {
        return new Customer(
                CustomerId.generate(),
                command.firstName(),
                command.lastName(),
                new NIT(command.nit()),
                new PhoneNumber(command.phoneNumber()));
    }

    public static Customer updateCustomer(Customer existing, UpdateCustomerCommand command) {
        String updatedFirstName = command.firstName() != null ? command.firstName() : existing.getFirstName();
        String updatedLastName = command.lastName() != null ? command.lastName() : existing.getLastName();
        String updatedNit = command.nit() != null ? command.nit() : existing.getNit().value();
        String updatedPhone = command.phoneNumber() != null ? command.phoneNumber() : existing.getPhoneNumber().value();

        Customer updated = new Customer(
                existing.getId(),
                updatedFirstName,
                updatedLastName,
                new NIT(updatedNit),
                new PhoneNumber(updatedPhone));

        if (!existing.isActive()) {
            updated.deactivate();
        }

        return updated;
    }
}
