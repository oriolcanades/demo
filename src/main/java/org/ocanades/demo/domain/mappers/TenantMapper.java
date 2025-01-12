package org.ocanades.demo.domain.mappers;

import org.ocanades.demo.application.dto.TenantRequest;
import org.ocanades.demo.application.dto.TenantResponse;
import org.ocanades.demo.domain.entities.Tenant;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

    public Tenant toEntity(TenantRequest tenantRequest) {
        return Tenant.builder()
                .name(tenantRequest.name())
                .build();
    }

    public TenantResponse toDto(Tenant tenant) {
        return TenantResponse.builder()
                .id(tenant.getId().toString())
                .name(tenant.getName())
                .build();
    }

}
