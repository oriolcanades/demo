package org.ocanades.demo.application.workflows;

import org.ocanades.demo.application.dto.TenantResponse;

import java.util.List;

public interface GetTenantWorkflow {
    List<TenantResponse> getAll();
    TenantResponse get(String id);
}
