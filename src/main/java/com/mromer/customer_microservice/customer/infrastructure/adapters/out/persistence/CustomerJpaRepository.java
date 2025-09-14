package com.mromer.customer_microservice.customer.infrastructure.adapters.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID>,  JpaSpecificationExecutor<CustomerEntity> {
    Optional<CustomerEntity> findByNit(String nit);
    boolean existsByNit(String nit);
    boolean existsByNitAndIdNot(String nit, UUID id);
}
