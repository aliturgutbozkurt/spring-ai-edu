package com.springai.edu.module12.controller;

import com.springai.edu.module12.service.ProductionDeploymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/deployment")
public class DeploymentStatusController {

    private final ProductionDeploymentService deploymentService;

    public DeploymentStatusController(ProductionDeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        return ResponseEntity.ok(deploymentService.getDeploymentMetrics());
    }
}
