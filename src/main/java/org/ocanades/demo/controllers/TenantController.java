package org.ocanades.demo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ocanades.demo.models.TenantRequest;
import org.ocanades.demo.models.TenantResponse;
import org.ocanades.demo.services.TenantService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private static final String SERVICE_URL = "http://localhost:8080/api/v1/tenants/";

    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<Void> createTenant(@Valid @RequestBody TenantRequest tenantRequest) {
        String tenantId = tenantService.createTenant(tenantRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", SERVICE_URL + tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(responseHeaders).build();
    }

    @GetMapping
    public ResponseEntity<List<TenantResponse>> getTenants() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tenantService.getTenants());
    }

    @PutMapping()
    public ResponseEntity<Void> updateTenant(@Valid @RequestBody TenantRequest tenantRequest) {
        tenantService.updateTenant(tenantRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", SERVICE_URL + tenantRequest.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .headers(responseHeaders).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponse> getTenant(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tenantService.getTenant(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable String id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
