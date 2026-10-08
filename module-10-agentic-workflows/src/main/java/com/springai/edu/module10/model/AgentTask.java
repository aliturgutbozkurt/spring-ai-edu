package com.springai.edu.module10.model;

public record AgentTask(
        String taskId,
        String goal,
        String status,
        int iterationCount,
        String finalAnswer
) {}
