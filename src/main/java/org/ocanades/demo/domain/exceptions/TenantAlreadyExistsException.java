package org.ocanades.demo.domain.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TenantAlreadyExistsException extends RuntimeException {

    private final String errorId;

    public TenantAlreadyExistsException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

}
