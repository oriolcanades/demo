package org.ocanades.demo.application.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteTenantException extends RuntimeException {

    private final String errorId;

    public DeleteTenantException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

}
