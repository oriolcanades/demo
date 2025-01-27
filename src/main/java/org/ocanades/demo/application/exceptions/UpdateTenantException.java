package org.ocanades.demo.application.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateTenantException extends RuntimeException {

    private final String errorId;

    public UpdateTenantException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

}
