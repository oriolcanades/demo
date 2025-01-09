package org.ocanades.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TenantResponse(
        @JsonProperty("tenant_id") String id,
        @JsonProperty("name") String name
) {
}
