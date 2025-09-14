package com.mromer.customer_microservice.common.infrastructure.exception;

import com.mromer.customer_microservice.customer.application.exceptions.CustomerNotFoundException;
import com.mromer.customer_microservice.customer.application.exceptions.DuplicateNITException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    ProblemDetail handleCustomerNotFound(CustomerNotFoundException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Customer Not Found");
        pd.setProperty("error_category", "Business Rule");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    @ExceptionHandler(DuplicateNITException.class)
    ProblemDetail handleDuplicateNIT(DuplicateNITException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        pd.setTitle("Duplicate NIT");
        pd.setProperty("error_category", "Business Rule");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            errors.put(field, msg);
        });

        String detail = errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle("Validation Error");
        pd.setDetail(detail);
        pd.setProperty("error_category", "Validation");
        pd.setProperty("timestamp", Instant.now());
        pd.setProperty("errors", errors);

        return new ResponseEntity<>(pd, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraintViolation(ConstraintViolationException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        pd.setTitle("Constraint Violation");
        pd.setProperty("error_category", "Validation");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleIllegalArgument(IllegalArgumentException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        pd.setTitle("Illegal Argument");
        pd.setProperty("error_category", "Domain");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    @ExceptionHandler(NullPointerException.class)
    ProblemDetail handleNullPointer(NullPointerException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected null value: " + e.getMessage());
        pd.setTitle("Null Pointer");
        pd.setProperty("error_category", "Domain");
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }
}
