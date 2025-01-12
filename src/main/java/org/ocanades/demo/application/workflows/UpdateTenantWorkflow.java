package org.ocanades.demo.application.workflows;

import org.ocanades.demo.application.dto.TenantRequest;

public interface UpdateTenantWorkflow {
    void execute(String id, TenantRequest tenantRequest);
}
