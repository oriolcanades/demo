package org.ocanades.demo.application.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReadTenantException extends RuntimeException {

    private final String errorId;

    public ReadTenantException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

}
