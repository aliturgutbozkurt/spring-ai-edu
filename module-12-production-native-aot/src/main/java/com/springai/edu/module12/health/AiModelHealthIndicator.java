package com.springai.edu.module12.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AiModelHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        long pingStart = System.currentTimeMillis();

        // Simulated AI provider ping check
        boolean available = true;
        long latencyMs = System.currentTimeMillis() - pingStart;

        if (available && latencyMs < 2000) {
            return Health.up()
                    .withDetail("provider", "Ollama / MockProvider")
                    .withDetail("latencyMs", latencyMs)
                    .withDetail("status", "AVAILABLE")
                    .build();
        } else {
            return Health.down()
                    .withDetail("error", "High latency or provider offline")
                    .build();
        }
    }
}
