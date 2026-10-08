package com.springai.edu.module01.service;

import com.springai.edu.module01.dto.ChatPromptRequest;
import com.springai.edu.module01.dto.ModelHealthStatus;
import com.springai.edu.module01.dto.MultiModelComparisonResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.ai.ollama.chat.enabled=false"
})
class MultiModelComparisonServiceTest {

    @Autowired
    private MultiModelComparisonService comparisonService;

    @Test
    @DisplayName("Should probe health of configured model providers")
    void shouldProbeModelHealth() {
        List<ModelHealthStatus> healthList = comparisonService.checkHealth();

        assertNotNull(healthList);
        assertFalse(healthList.isEmpty(), "At least one model provider must be registered");

        ModelHealthStatus mockStatus = healthList.stream()
                .filter(s -> s.providerName().equalsIgnoreCase("mock"))
                .findFirst()
                .orElse(null);

        assertNotNull(mockStatus, "MockChatModel status must be reported");
        assertTrue(mockStatus.available(), "MockChatModel should be available");
    }

    @Test
    @DisplayName("Should execute multi-model comparison concurrently on Virtual Threads")
    void shouldExecuteConcurrentComparison() {
        ChatPromptRequest request = new ChatPromptRequest("ping");
        MultiModelComparisonResult result = comparisonService.compareModels(request);

        assertNotNull(result);
        assertEquals("ping", result.prompt());
        assertFalse(result.responses().isEmpty(), "Responses must not be empty");
        assertTrue(result.totalDurationMs() >= 0, "Duration must be positive");
    }
}
