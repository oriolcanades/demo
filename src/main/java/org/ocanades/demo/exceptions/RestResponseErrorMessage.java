package org.ocanades.demo.exceptions;

import lombok.Builder;

@Builder
public record RestResponseErrorMessage(
        String type,
        String title,
        int status,
        String detail,
        String instance
) {
}
