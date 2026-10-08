package com.springai.edu.module11.model;

import java.util.List;

public record SecurePromptResponse(
        String sanitizedPrompt,
        String response,
        boolean piiDetected,
        List<String> redactedPiiTypes,
        boolean injectionAttemptBlocked,
        long latencyMs
) {}
