package com.springai.edu.homework01;

import com.springai.edu.common.mock.MockChatModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MultiModelComparisonServiceTest {

    private MultiModelComparisonService service;

    @BeforeEach
    void setUp() {
        MockChatModel modelA = new MockChatModel("Response from Model Alpha");
        MockChatModel modelB = new MockChatModel("Response from Model Beta");

        service = new MultiModelComparisonService(Map.of(
                "model-alpha", modelA,
                "model-beta", modelB
        ));
    }

    @Test
    @DisplayName("Should execute comparison across all models concurrently")
    void shouldExecuteConcurrentlyAcrossModels() {
        var summary = service.compareAcrossModels("Explain virtual threads");

        assertNotNull(summary);
        assertEquals("Explain virtual threads", summary.prompt());
        assertEquals(2, summary.results().size(), "Must execute against all 2 models");
        assertTrue(summary.results().stream().allMatch(MultiModelComparisonService.ProviderResult::success));
        assertTrue(summary.totalWallClockMs() >= 0);
    }

    @Test
    @DisplayName("Should handle single provider failure without failing entire batch")
    void shouldIsolateProviderFailure() {
        MockChatModel healthyModel = new MockChatModel("Healthy response");
        MockChatModel failingModel = new MockChatModel(prompt -> {
            throw new RuntimeException("Simulated connection timeout to model provider");
        });

        MultiModelComparisonService resilientService = new MultiModelComparisonService(Map.of(
                "healthy", healthyModel,
                "failing", failingModel
        ));

        var summary = resilientService.compareAcrossModels("Test resilience");

        assertNotNull(summary);
        assertEquals(2, summary.results().size());

        long successfulCount = summary.results().stream().filter(MultiModelComparisonService.ProviderResult::success).count();
        long failedCount = summary.results().stream().filter(r -> !r.success()).count();

        assertEquals(1, successfulCount, "One model should succeed");
        assertEquals(1, failedCount, "One model should fail gracefully");
    }
}
