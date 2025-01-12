package org.ocanades.demo.application.workflows.impl;

import lombok.RequiredArgsConstructor;
import org.ocanades.demo.application.dto.TenantResponse;
import org.ocanades.demo.application.workflows.GetTenantWorkflow;
import org.ocanades.demo.domain.entities.Tenant;
import org.ocanades.demo.domain.mappers.TenantMapper;
import org.ocanades.demo.domain.services.TenantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetTenantWorkflowImpl implements GetTenantWorkflow {

    private final TenantMapper tenantMapper;
    private final TenantService tenantService;

    @Override
    public List<TenantResponse> getAll() {
        List<Tenant> tenantList = tenantService.getTenants();
        return tenantList.stream()
                .map(tenantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TenantResponse get(String id) {
        Tenant tenantFound = tenantService.getTenant(id);
        return tenantMapper.toDto(tenantFound);
    }

}
