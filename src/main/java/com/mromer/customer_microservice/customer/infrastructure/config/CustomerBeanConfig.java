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
    public CreatingCustomerInputPort creatingCustomer(StoringCustomerOutputPort outputPort,
                                                      FindingCustomersOutputPort findingOutputPort) {
        return new CreateCustomerService(outputPort, findingOutputPort);
    }

    @Bean
    public UpdateCustomerInputPort updatingCustomer(StoringCustomerOutputPort outputPort,
                                                    FindingCustomersOutputPort findingOutputPort) {
        return new UpdateCustomerService(outputPort, findingOutputPort);
    }

    @Bean
    public DeactivatingCustomerInputPort deactivatingCustomer(FindingCustomersOutputPort findingOutputPort,
                                                              StoringCustomerOutputPort storingOutputPort) {
        return new DeactivateCustomerService(findingOutputPort, storingOutputPort);
    }

    @Bean
    public FindingCustomerInputPort findingCustomer(FindingCustomersOutputPort findingOutputPort) {
        return new FindCustomerService(findingOutputPort);
    }
}

