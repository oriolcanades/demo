package org.ocanades.demo.application.workflows.impl;

import lombok.RequiredArgsConstructor;
import org.ocanades.demo.application.workflows.DeleteTenantWorkflow;
import org.ocanades.demo.domain.services.TenantService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTenantWorkflowImpl implements DeleteTenantWorkflow {

    private final TenantService tenantService;

    @Override
    public void execute(String id) {
        tenantService.deleteTenant(id);
    }

}
