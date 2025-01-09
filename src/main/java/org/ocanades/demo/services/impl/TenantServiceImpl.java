package org.ocanades.demo.services.impl;

import lombok.RequiredArgsConstructor;
import org.ocanades.demo.models.Tenant;
import org.ocanades.demo.models.TenantRequest;
import org.ocanades.demo.models.TenantResponse;
import org.ocanades.demo.repositories.TenantRepository;
import org.ocanades.demo.services.TenantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    @Override
    public String createTenant(TenantRequest tenantRequest) {
        Tenant tenant = Tenant.builder()
                .name(tenantRequest.name())
                .build();
        try {
            tenantRepository.save(tenant);
            return tenant.getId().toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("Tenant already exists");
        }
    }

    @Override
    public List<TenantResponse> getTenants() {
        List<Tenant> tenantFoundList = tenantRepository.findAll();
        if (!tenantFoundList.isEmpty()) {
            return tenantFoundList.stream()
                    .map(tenant -> new TenantResponse(tenant.getId().toString(), tenant.getName()))
                    .toList();
        } else {
            throw new NoSuchElementException("No tenants found");
        }
    }

    @Override
    public void updateTenant(TenantRequest tenantRequest) {
        Tenant tenantFound = tenantRepository.findById(UUID.fromString(tenantRequest.id()))
                .orElseThrow(() -> new NoSuchElementException("Tenant not found"));
        try {
            Tenant updatedTenant = Tenant.builder()
                    .id(tenantFound.getId())
                    .name(tenantRequest.name())
                    .build();
            tenantRepository.save(updatedTenant);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tenant already exists");
        }
    }

    @Override
    public TenantResponse getTenant(String tenantId) {
        Tenant tenantFound = tenantRepository.findById(UUID.fromString(tenantId))
                .orElseThrow(() -> new NoSuchElementException("Tenant not found"));
        return TenantResponse.builder()
                .id(tenantFound.getId().toString())
                .name(tenantFound.getName())
                .build();
    }

    @Override
    public void deleteTenant(String tenantId) {
        tenantRepository.deleteById(UUID.fromString(tenantId));
    }

}
