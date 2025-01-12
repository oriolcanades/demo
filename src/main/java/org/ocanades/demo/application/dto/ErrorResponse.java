package org.ocanades.demo.application.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String type,
        String title,
        int status,
        String detail,
        String instance
) {
}
