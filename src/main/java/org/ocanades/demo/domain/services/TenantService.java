package org.ocanades.demo.domain.services;

import org.ocanades.demo.domain.entities.Tenant;

import java.util.List;

public interface TenantService {
    String createTenant(Tenant tenant);
    List<Tenant> getTenants();
    void updateTenant(String tenantId, Tenant tenant);
    Tenant getTenant(String tenantId);
    void deleteTenant(String tenantId);
}
