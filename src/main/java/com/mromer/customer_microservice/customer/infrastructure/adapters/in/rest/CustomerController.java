package com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest;

import com.mromer.customer_microservice.common.infrastructure.adapters.in.dto.PagedResponseDTO;
import com.mromer.customer_microservice.customer.application.port.in.*;
import com.mromer.customer_microservice.customer.domain.Customer;
import com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto.CreateCustomerRequestDTO;
import com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto.CustomerResponseDTO;
import com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto.FindCustomerRequestDTO;
import com.mromer.customer_microservice.customer.infrastructure.adapters.in.rest.dto.UpdateCustomerRequestDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
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

    private final CreateCustomerInputPort creatingCustomerInputPort;
    private final UpdateCustomerInputPort updateCustomerInputPort;
    private final DeactivateCustomerInputPort deactivatingCustomerInputPort;
    private final FindCustomerInputPort findingCustomerInputPort;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CreateCustomerRequestDTO requestDTO) {
        
        CreateCustomerCommand command = new CreateCustomerCommand(
                requestDTO.firstName(),
                requestDTO.lastName(),
                requestDTO.nit(),
                requestDTO.phoneNumber()
        );
        Customer created = creatingCustomerInputPort.createCustomer(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerRestMapper.toResponseDTO(created));
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @RequestBody @Valid UpdateCustomerRequestDTO requestDTO) {

        UpdateCustomerCommand command = new UpdateCustomerCommand(
                requestDTO.id(),
                requestDTO.firstName(),
                requestDTO.lastName(),
                requestDTO.nit(),
                requestDTO.phoneNumber()
        );

        Customer updated = updateCustomerInputPort.updateCustomer(command);
        return ResponseEntity.ok(CustomerRestMapper.toResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable UUID id) {
        deactivatingCustomerInputPort.deactivateCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable UUID id) {
        Customer customer = findingCustomerInputPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(CustomerRestMapper.toResponseDTO(customer));
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<CustomerResponseDTO>> getAllCustomers(
            @Valid FindCustomerRequestDTO requestDTO,
            @PageableDefault Pageable pageable) {

        FindCustomersCommand command = new FindCustomersCommand(
                requestDTO.firstName(),
                requestDTO.lastName(),
                requestDTO.nit(),
                requestDTO.active());

        Page<Customer> page = findingCustomerInputPort.findAll(command, pageable);
        PagedResponseDTO<CustomerResponseDTO> response = new PagedResponseDTO<>(
                page.getContent().stream().map(CustomerRestMapper::toResponseDTO).toList(),
                page.getTotalElements(),
                page.getNumber(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
        return ResponseEntity.ok(response);
    }
}

