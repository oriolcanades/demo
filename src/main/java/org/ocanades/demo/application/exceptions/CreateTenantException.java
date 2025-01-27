package org.ocanades.demo.application.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateTenantException extends RuntimeException {

    private final String errorId;

    public CreateTenantException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

}
