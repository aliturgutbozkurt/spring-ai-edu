package com.springai.edu.module10;

import com.springai.edu.module10.service.MultiAgentSupervisorService;
import com.springai.edu.module10.service.ReActAgentEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MultiAgentSupervisorServiceTest {

    private final ReActAgentEngine engine = new ReActAgentEngine();
    private final MultiAgentSupervisorService supervisor = new MultiAgentSupervisorService(engine);

    @Test
    @DisplayName("Should coordinate multi-agent squad and return synthesized deliverables")
    void shouldCoordinateMultiAgents() {
        Map<String, Object> output = supervisor.delegateComplexProject("Enterprise AI CRM Migration");

        assertThat(output).containsKey("mission");
        assertThat(output.get("status")).isEqualTo("DELIVERED");
        assertThat(output.get("approvalStatus")).isEqualTo("APPROVED_BY_SUPERVISOR");
    }
}
