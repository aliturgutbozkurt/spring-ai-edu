package com.springai.edu.module12;

import com.springai.edu.module12.controller.DeploymentStatusController;
import com.springai.edu.module12.service.ProductionDeploymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeploymentStatusController.class)
@Import(ProductionDeploymentService.class)
@DisplayName("Module 12 - Deployment Status Controller WebMvc Tests")
class DeploymentStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/deployment/status should return 200 OK and deployment telemetry")
    void shouldReturnDeploymentStatus() throws Exception {
        mockMvc.perform(get("/api/v1/deployment/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.runtime").exists())
                .andExpect(jsonPath("$.virtualThreadsEnabled").value(true))
                .andExpect(jsonPath("$.deploymentProfile").value("PRODUCTION_READY"));
    }
}
