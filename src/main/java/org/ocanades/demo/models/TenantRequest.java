package org.ocanades.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TenantRequest(
        @JsonProperty("tenant_id") String id,
        @JsonProperty("name") @NotNull @NotBlank String name
) {
}
