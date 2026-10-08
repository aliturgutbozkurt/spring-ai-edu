package com.springai.edu.capstone;

import com.springai.edu.capstone.agent.EnterpriseSupervisorAgent;
import com.springai.edu.capstone.agent.KnowledgeRagSubagent;
import com.springai.edu.capstone.agent.OperationsToolSubagent;
import com.springai.edu.capstone.model.CapstoneRequest;
import com.springai.edu.capstone.model.CapstoneResponse;
import com.springai.edu.common.mock.MockChatModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capstone - Enterprise Supervisor Agent Orchestration Tests")
class EnterpriseSupervisorAgentTest {

    private EnterpriseSupervisorAgent supervisorAgent;

    @BeforeEach
    void setUp() {
        MockChatModel chatModel = new MockChatModel();
        KnowledgeRagSubagent ragSubagent = new KnowledgeRagSubagent(chatModel);
        OperationsToolSubagent opsSubagent = new OperationsToolSubagent();
        supervisorAgent = new EnterpriseSupervisorAgent(ragSubagent, opsSubagent);
    }

    @Test
    @DisplayName("Should route policy inquiry to KnowledgeRagSubagent")
    void shouldRouteToRagSubagent() {
        CapstoneRequest request = new CapstoneRequest(
                "What is our vacation policy for full-time employees?",
                "user-101",
                "HR"
        );

        CapstoneResponse response = supervisorAgent.processRequest(request);

        assertThat(response.delegatedAgent()).isEqualTo("KnowledgeRagSubagent");
        assertThat(response.answer()).isNotBlank();
        assertThat(response.auditTrail())
                .anyMatch(step -> step.contains("Delegated request to KnowledgeRagSubagent"));
    }

    @Test
    @DisplayName("Should route server or ticket incident to OperationsToolSubagent")
    void shouldRouteToOperationsSubagent() {
        CapstoneRequest request = new CapstoneRequest(
                "Please check the server status of all cluster nodes",
                "ops-admin",
                "INFRA"
        );

        CapstoneResponse response = supervisorAgent.processRequest(request);

        assertThat(response.delegatedAgent()).isEqualTo("OperationsToolSubagent");
        assertThat(response.answer()).contains("HEALTHY");
        assertThat(response.auditTrail())
                .anyMatch(step -> step.contains("Delegated request to OperationsToolSubagent"));
    }

    @Test
    @DisplayName("Should mask credit card PII before delegating to subagents")
    void shouldRedactCreditCardPii() {
        CapstoneRequest request = new CapstoneRequest(
                "My credit card is 4111 2222 3333 4444, please update billing for ticket 99",
                "cust-55",
                "BILLING"
        );

        CapstoneResponse response = supervisorAgent.processRequest(request);

        assertThat(response.metadata().get("sanitizedQuery").toString())
                .doesNotContain("4111 2222 3333 4444")
                .contains("[REDACTED_CARD]");

        assertThat(response.auditTrail())
                .anyMatch(step -> step.contains("Detected sensitive card number"));
    }
}
