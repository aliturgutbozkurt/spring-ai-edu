package com.springai.edu.module10.model;

import java.util.List;

public record AgentExecutionReport(
        String taskId,
        String goal,
        String status,
        List<AgentStep> steps,
        String outcome,
        long executionDurationMs
) {}
