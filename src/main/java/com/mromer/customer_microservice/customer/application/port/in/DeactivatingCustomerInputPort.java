package com.mromer.customer_microservice.customer.application.port.in;

import java.util.UUID;

public interface DeactivatingCustomerInputPort {
    void deactivateCustomer(UUID id);
}
