package com.springai.edu.module10.model;

public record AgentStep(
        int stepNumber,
        String thought,
        String action,
        String observation
) {}
