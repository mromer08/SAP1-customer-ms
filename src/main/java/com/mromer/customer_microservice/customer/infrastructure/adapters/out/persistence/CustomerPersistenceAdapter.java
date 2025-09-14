package com.mromer.customer_microservice.customer.infrastructure.adapters.out.persistence;

import com.mromer.customer_microservice.customer.application.port.out.persistence.FindingCustomersOutputPort;
import com.mromer.customer_microservice.customer.application.port.out.persistence.StoringCustomerOutputPort;
import com.mromer.customer_microservice.customer.application.port.in.FindCustomersCommand;
import com.mromer.customer_microservice.customer.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerPersistenceAdapter implements StoringCustomerOutputPort, FindingCustomersOutputPort {

    private final CustomerJpaRepository repository;

    public CustomerPersistenceAdapter(CustomerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = CustomerEntityMapper.toEntity(customer);
        CustomerEntity saved = repository.save(entity);
        return CustomerEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return repository.findById(id).map(CustomerEntityMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByNit(String nit) {
        return repository.findByNit(nit).map(CustomerEntityMapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByNIT(String nit) {
        return repository.existsByNit(nit);
    }

    @Override
    public boolean existsByNITAndIdNot(String nit, UUID id) {
        return repository.existsByNitAndIdNot(nit, id);
    }

    @Override
    public Page<Customer> findAll(FindCustomersCommand command, Pageable pageable) {

        Specification<CustomerEntity> spec = Specification
                .allOf(
                        CustomerSpecs.firstNameContains(command.firstName()),
                        CustomerSpecs.lastNameContains(command.lastName()),
                        CustomerSpecs.activeEquals(command.active()),
                        CustomerSpecs.nitEquals(command.nit()));

        Page<CustomerEntity> page = repository.findAll(spec, pageable);

        List<Customer> customers = page.getContent().stream()
                .map(CustomerEntityMapper::toDomain)
                .toList();
        Page<Customer> pageResult = new PageImpl<>(customers, pageable, page.getTotalElements());

        return pageResult;
    }
}
