package com.springai.edu.module02.dto;

public record SqlGenerationResponse(
        String sqlQuery,
        String explanation,
        boolean injectionAttemptDetected,
        long durationMs
) {}
