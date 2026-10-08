package com.springai.edu.capstone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springai.edu.capstone.agent.EnterpriseSupervisorAgent;
import com.springai.edu.capstone.agent.KnowledgeRagSubagent;
import com.springai.edu.capstone.agent.OperationsToolSubagent;
import com.springai.edu.capstone.config.CapstoneConfig;
import com.springai.edu.capstone.controller.EnterpriseAssistantController;
import com.springai.edu.capstone.model.CapstoneRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnterpriseAssistantController.class)
@Import({CapstoneConfig.class, EnterpriseSupervisorAgent.class, KnowledgeRagSubagent.class, OperationsToolSubagent.class})
@DisplayName("Capstone - Enterprise Assistant Controller Tests")
class EnterpriseAssistantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/v1/assistant/chat should return 200 OK with routed agent and audit trail")
    void shouldHandleChatRequest() throws Exception {
        CapstoneRequest request = new CapstoneRequest("What is our remote work policy?", "user-42", "HR");

        mockMvc.perform(post("/api/v1/assistant/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.delegatedAgent").value("KnowledgeRagSubagent"))
                .andExpect(jsonPath("$.auditTrail").isArray())
                .andExpect(jsonPath("$.metadata.executionEngine").exists());
    }

    @Test
    @DisplayName("POST /api/v1/assistant/chat with blank query should fail validation with 400 Bad Request")
    void shouldRejectBlankQuery() throws Exception {
        CapstoneRequest request = new CapstoneRequest("", "user-42", "HR");

        mockMvc.perform(post("/api/v1/assistant/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
