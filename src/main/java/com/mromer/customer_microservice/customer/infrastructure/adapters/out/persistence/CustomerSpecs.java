package com.mromer.customer_microservice.customer.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecs {

    public static Specification<CustomerEntity> firstNameContains(String firstName) {
        return (root, query, criteriaBuilder) ->
                (firstName == null || firstName.isBlank()) 
                        ? null 
                        : criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("firstName")), 
                            "%" + firstName.toLowerCase() + "%"
                        );
    }

    public static Specification<CustomerEntity> lastNameContains(String lastName) {
        return (root, query, criteriaBuilder) ->
                (lastName == null || lastName.isBlank()) 
                        ? null 
                        : criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("lastName")), 
                            "%" + lastName.toLowerCase() + "%"
                        );
    }

    public static Specification<CustomerEntity> nitEquals(String nit) {
        return (root, query, criteriaBuilder) ->
                (nit == null || nit.isBlank()) 
                        ? null 
                        : criteriaBuilder.equal(root.get("nit"), nit);
    }
}
