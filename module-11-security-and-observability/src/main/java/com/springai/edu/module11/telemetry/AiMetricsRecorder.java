package com.springai.edu.module11.telemetry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AiMetricsRecorder {

    private final Counter requestCounter;
    private final Counter injectionBlockedCounter;
    private final Counter piiRedactedCounter;
    private final Timer inferenceTimer;

    public AiMetricsRecorder(MeterRegistry registry) {
        this.requestCounter = Counter.builder("spring.ai.requests.total")
                .description("Total Spring AI prompt requests")
                .register(registry);

        this.injectionBlockedCounter = Counter.builder("spring.ai.security.injections.blocked")
                .description("Total blocked prompt injection attempts")
                .register(registry);

        this.piiRedactedCounter = Counter.builder("spring.ai.security.pii.redacted")
                .description("Total PII items redacted")
                .register(registry);

        this.inferenceTimer = Timer.builder("spring.ai.inference.duration")
                .description("Latency of AI model responses")
                .register(registry);
    }

    public void recordRequest() {
        requestCounter.increment();
    }

    public void recordInjectionBlocked() {
        injectionBlockedCounter.increment();
    }

    public void recordPiiRedacted(int count) {
        piiRedactedCounter.increment(count);
    }

    public void recordInferenceDuration(long durationMs) {
        inferenceTimer.record(durationMs, TimeUnit.MILLISECONDS);
    }
}
