package com.springai.edu.module12;

import com.springai.edu.module12.health.AiModelHealthIndicator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Module 12 - AI Model Actuator Health Indicator Tests")
class AiModelHealthIndicatorTest {

    @Test
    @DisplayName("Should report UP status when AI provider is reachable")
    void shouldReportUpStatus() {
        AiModelHealthIndicator indicator = new AiModelHealthIndicator();
        Health health = indicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails()).containsKey("provider");
        assertThat(health.getDetails()).containsKey("latencyMs");
    }
}
