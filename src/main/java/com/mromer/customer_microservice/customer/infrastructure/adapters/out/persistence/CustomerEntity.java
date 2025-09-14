package com.mromer.customer_microservice.customer.infrastructure.adapters.out.persistence;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class CustomerEntity {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String nit;
    private String phoneNumber;
    private boolean active;

}
