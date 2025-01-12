package org.ocanades.demo.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TenantRequest(@NotNull @NotBlank String name) {
}
