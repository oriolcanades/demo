package org.ocanades.demo.application.workflows.impl;

import lombok.RequiredArgsConstructor;
import org.ocanades.demo.application.dto.TenantRequest;
import org.ocanades.demo.application.exceptions.UpdateTenantException;
import org.ocanades.demo.application.mappers.TenantMapper;
import org.ocanades.demo.application.workflows.UpdateTenantWorkflow;
import org.ocanades.demo.domain.entities.Tenant;
import org.ocanades.demo.domain.services.TenantService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTenantWorkflowImpl implements UpdateTenantWorkflow {

    private final TenantMapper tenantMapper;
    private final TenantService tenantService;

    @Override
    public void execute(String id, TenantRequest tenantRequest) {
        try {
            Tenant tenant = tenantMapper.toEntity(tenantRequest);
            tenantService.updateTenant(id, tenant);
        } catch (Exception e) {
            throw new UpdateTenantException("Error updating tenant");
        }
    }

}
