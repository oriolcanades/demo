package org.ocanades.demo.infrastructure.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ocanades.demo.application.dto.TenantRequest;
import org.ocanades.demo.application.dto.TenantResponse;
import org.ocanades.demo.application.workflows.CreateTenantWorkflow;
import org.ocanades.demo.application.workflows.DeleteTenantWorkflow;
import org.ocanades.demo.application.workflows.GetTenantWorkflow;
import org.ocanades.demo.application.workflows.UpdateTenantWorkflow;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private static final String SERVICE_URL = "http://localhost:8080/api/v1/tenants/";

    private final CreateTenantWorkflow createTenantWorkflow;
    private final UpdateTenantWorkflow updateTenantWorkflow;
    private final GetTenantWorkflow getTenantWorkflow;
    private final DeleteTenantWorkflow deleteTenantWorkflow;

    @PostMapping
    public ResponseEntity<Void> createTenant(@Valid @RequestBody TenantRequest tenantRequest) {
        log.info("Creating tenant: {}", tenantRequest);
        String tenantId = createTenantWorkflow.execute(tenantRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", SERVICE_URL + tenantId);
        log.info("Tenant created with id: {}", tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(responseHeaders).build();
    }

    @GetMapping
    public ResponseEntity<List<TenantResponse>> getTenants() {
        log.info("Getting all tenants");
        List<TenantResponse> tenants = getTenantWorkflow.getAll();
        log.info("Tenants found: {}", tenants.size());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tenants);
    }

    @PutMapping("/{tenantId}")
    public ResponseEntity<Void> updateTenant(@PathVariable String tenantId,
                                             @Valid @RequestBody TenantRequest tenantRequest) {
        log.info("Updating tenant with id: {}", tenantId);
        updateTenantWorkflow.execute(tenantId, tenantRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", SERVICE_URL + tenantId);
        log.info("Tenant updated with id: {}", tenantId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .headers(responseHeaders).build();
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<TenantResponse> getTenant(@PathVariable String tenantId) {
        log.info("Getting tenant with id: {}", tenantId);
        TenantResponse tenant = getTenantWorkflow.get(tenantId);
        log.info("Tenant found: {}", tenant);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tenant);
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<Void> deleteTenant(@PathVariable String tenantId) {
        log.info("Deleting tenant with id: {}", tenantId);
        deleteTenantWorkflow.execute(tenantId);
        log.info("Tenant deleted with id: {}", tenantId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
