package org.ocanades.demo.application.workflows;

import org.ocanades.demo.application.dto.TenantRequest;

public interface CreateTenantWorkflow {
    String execute(TenantRequest tenantRequest);
}
