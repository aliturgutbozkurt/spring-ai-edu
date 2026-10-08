package com.springai.edu.module10;

import com.springai.edu.module10.model.AgentExecutionReport;
import com.springai.edu.module10.service.ReActAgentEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReActAgentEngineTest {

    private final ReActAgentEngine engine = new ReActAgentEngine();

    @Test
    @DisplayName("Should execute iterative ReAct reasoning loop until task is resolved")
    void shouldExecuteReActLoop() {
        AgentExecutionReport report = engine.executeGoal("Analyze European market expansion for AI services");

        assertThat(report).isNotNull();
        assertThat(report.status()).isEqualTo("COMPLETED");
        assertThat(report.steps()).hasSize(3);
        assertThat(report.steps().get(0).thought()).contains("search the database");
        assertThat(report.outcome()).contains("Executive Report");
    }
}
