package com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest;

import com.mromer.customer_microservice.common.application.PagedResponseDTO;
import com.mromer.customer_microservice.customer.application.port.in.*;
import com.mromer.customer_microservice.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreatingCustomerInputPort creatingCustomerInputPort;
    private final UpdateCustomerInputPort updateCustomerInputPort;
    private final DeactivatingCustomerInputPort deactivatingCustomerInputPort;
    private final FindingCustomerInputPort findingCustomerInputPort;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerCommand command) {
        Customer created = creatingCustomerInputPort.createCustomer(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(
            @RequestBody @Valid UpdateCustomerCommand command) {

        Customer updated = updateCustomerInputPort.updateCustomer(command);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable UUID id) {
        deactivatingCustomerInputPort.deactivateCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
        Customer customer = findingCustomerInputPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<Customer>> getAllCustomers(
            @Valid FindCustomersCommand command,
            @PageableDefault Pageable pageable) {

        PagedResponseDTO<Customer> page = findingCustomerInputPort.findAll(command, pageable);
        return ResponseEntity.ok(page);
    }
}

