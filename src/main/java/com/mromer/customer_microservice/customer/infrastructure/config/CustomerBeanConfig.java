package com.mromer.customer_microservice.customer.infrastructure.config;

import com.mromer.customer_microservice.customer.application.port.in.*;
import com.mromer.customer_microservice.customer.application.services.*;
import com.mromer.customer_microservice.customer.application.port.out.persistence.*;
import com.mromer.customer_microservice.customer.infrastructure.adapters.out.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBeanConfig {

    // Adapter de persistencia
    @Bean
    public CustomerPersistenceAdapter customerPersistenceAdapter(CustomerJpaRepository repository) {
        return new CustomerPersistenceAdapter(repository);
    }

    // Beans de servicios de aplicación (Input Ports)
    @Bean
    public CreateCustomerInputPort creatingCustomer(StoreCustomerOutputPort outputPort,
                                                      FindCustomersOutputPort findingOutputPort) {
        return new CreateCustomerService(outputPort, findingOutputPort);
    }

    @Bean
    public UpdateCustomerInputPort updatingCustomer(StoreCustomerOutputPort outputPort,
                                                    FindCustomersOutputPort findingOutputPort) {
        return new UpdateCustomerService(outputPort, findingOutputPort);
    }

    @Bean
    public DeactivateCustomerInputPort deactivatingCustomer(FindCustomersOutputPort findingOutputPort,
                                                              StoreCustomerOutputPort storingOutputPort) {
        return new DeactivateCustomerService(findingOutputPort, storingOutputPort);
    }

    @Bean
    public FindCustomerInputPort findingCustomer(FindCustomersOutputPort findingOutputPort) {
        return new FindCustomerService(findingOutputPort);
    }
}

