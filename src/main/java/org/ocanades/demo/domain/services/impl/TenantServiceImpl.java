package org.ocanades.demo.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.ocanades.demo.domain.entities.Tenant;
import org.ocanades.demo.domain.exceptions.TenantAlreadyExistsException;
import org.ocanades.demo.domain.exceptions.TenantNotFoundException;
import org.ocanades.demo.domain.services.TenantService;
import org.ocanades.demo.domain.repositories.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private static final String TENANT_ALREADY_EXISTS_ERROR_MESSAGE = "Tenant already exists";
    private static final String TENANT_NOT_FOUND_ERROR_MESSAGE = "Tenant or tenants not found";

    private final TenantRepository tenantRepository;

    @Override
    public String createTenant(Tenant tenant) {
        try {
            tenantRepository.save(tenant);
            return tenant.getId().toString();
        } catch (Exception e) {
            throw new TenantAlreadyExistsException(TENANT_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Tenant> getTenants() {
        List<Tenant> tenantFoundList = tenantRepository.findAll();
        if (!tenantFoundList.isEmpty()) {
            return tenantFoundList;
        } else {
            throw new TenantNotFoundException(TENANT_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    @Override
    public void updateTenant(String tenantId, Tenant tenant) {
        Tenant tenantFound = tenantRepository.findById(UUID.fromString(tenantId))
                .orElseThrow(() -> new TenantNotFoundException(TENANT_NOT_FOUND_ERROR_MESSAGE));
        try {
            Tenant updatedTenant = Tenant.builder()
                    .id(tenantFound.getId())
                    .name(tenant.getName())
                    .build();
            tenantRepository.save(updatedTenant);
        } catch (Exception e) {
            throw new TenantAlreadyExistsException(TENANT_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }

    @Override
    public Tenant getTenant(String tenantId) {
        return tenantRepository.findById(UUID.fromString(tenantId))
                .orElseThrow(() -> new TenantNotFoundException(TENANT_NOT_FOUND_ERROR_MESSAGE));
    }

    @Override
    public void deleteTenant(String tenantId) {
        tenantRepository.deleteById(UUID.fromString(tenantId));
    }

}
