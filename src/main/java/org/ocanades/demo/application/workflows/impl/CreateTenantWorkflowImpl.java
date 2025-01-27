package org.ocanades.demo.application.workflows.impl;

import lombok.RequiredArgsConstructor;
import org.ocanades.demo.application.dto.TenantRequest;
import org.ocanades.demo.application.exceptions.CreateTenantException;
import org.ocanades.demo.application.mappers.TenantMapper;
import org.ocanades.demo.application.workflows.CreateTenantWorkflow;
import org.ocanades.demo.domain.entities.Tenant;
import org.ocanades.demo.domain.services.TenantService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTenantWorkflowImpl implements CreateTenantWorkflow {

    private final TenantMapper tenantMapper;
    private final TenantService tenantService;

    @Override
    public String execute(TenantRequest tenantRequest) {
        try {
            Tenant tenant = tenantMapper.toEntity(tenantRequest);
            return tenantService.createTenant(tenant);
        } catch (Exception e) {
            throw new CreateTenantException("Error creating tenant with name: " + tenantRequest.name());
        }

    }

}
