package com.springai.edu.module01.dto;

/**
 * Health and operational status of a configured model provider.
 *
 * @param providerName The name of the provider ("ollama", "mock", "openai")
 * @param available    Whether the model is responsive
 * @param latencyMs    Probe latency in milliseconds
 * @param details      Additional status information or error message
 */
public record ModelHealthStatus(
        String providerName,
        boolean available,
        long latencyMs,
        String details
) {}
