package org.ocanades.demo.services;

import org.ocanades.demo.models.TenantRequest;
import org.ocanades.demo.models.TenantResponse;

import java.util.List;

public interface TenantService {
    String createTenant(TenantRequest tenantRequest);
    List<TenantResponse> getTenants();
    void updateTenant(TenantRequest tenantRequest);
    TenantResponse getTenant(String tenantId);
    void deleteTenant(String tenantId);
}
