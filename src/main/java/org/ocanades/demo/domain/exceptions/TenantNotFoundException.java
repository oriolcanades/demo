package org.ocanades.demo.domain.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TenantNotFoundException extends RuntimeException {

    private final String errorId;

    public TenantNotFoundException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

}
