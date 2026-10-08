package com.springai.edu.homework10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MarketResearchSquadTest {

    private final MarketResearchSquad squad = new MarketResearchSquad();

    @Test
    @DisplayName("Should execute sub-tasks and record completion status")
    void shouldExecuteSubTasks() {
        var subTasks = List.of("Extract pricing", "Analyze sentiment", "Synthesize findings");
        Map<String, String> results = squad.executeResearchPlan(subTasks);

        assertThat(results).hasSize(3);
        assertThat(results.get("Extract pricing")).isEqualTo("COMPLETED_BY_AGENT");
    }

    @Test
    @DisplayName("Should enforce human-in-the-loop approval gate")
    void shouldEnforceApprovalGate() {
        assertThat(squad.isApprovedByHuman(true)).isTrue();
        assertThat(squad.isApprovedByHuman(false)).isFalse();
    }
}
