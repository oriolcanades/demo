package org.ocanades.demo.infrastructure.controllers;

import lombok.extern.slf4j.Slf4j;
import org.ocanades.demo.application.dto.ErrorResponse;
import org.ocanades.demo.domain.exceptions.TenantAlreadyExistsException;
import org.ocanades.demo.domain.exceptions.TenantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = TenantAlreadyExistsException.class)
    private ResponseEntity<ErrorResponse> handleTenantAlreadyExistsException(TenantAlreadyExistsException ex) {
        log.error("Error ID: {} - {}", ex.getErrorId(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createRestResponseErrorMessage(HttpStatus.CONFLICT, ex));
    }

    @ExceptionHandler(value = TenantNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleTenantDoesNotExistException(TenantNotFoundException ex) {
        log.error("Error ID: {} - {}", ex.getErrorId(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createRestResponseErrorMessage(HttpStatus.NOT_FOUND, ex));
    }

    @ExceptionHandler(value = CannotCreateTransactionException.class)
    private ResponseEntity<ErrorResponse> handleCannotCreateTransactionException(CannotCreateTransactionException ex) {
        log.error("Error ID: {} - {}", UUID.randomUUID(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createRestResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    private ErrorResponse createRestResponseErrorMessage(HttpStatus status, Exception ex) {
        return ErrorResponse.builder()
                .type(ex.getClass().getSimpleName())
                .title(ex.getMessage())
                .status(status.value())
                .detail(ex.getLocalizedMessage())
                .instance("")
                .build();
    }

}
