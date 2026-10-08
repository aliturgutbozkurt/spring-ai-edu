package com.springai.edu.module09.model;

import java.util.Map;

public record McpToolCallResponse(
        String toolName,
        boolean success,
        Map<String, Object> result,
        String errorMessage,
        long executionTimeMs
) {
    public static McpToolCallResponse success(String toolName, Map<String, Object> result, long duration) {
        return new McpToolCallResponse(toolName, true, result, null, duration);
    }

    public static McpToolCallResponse failure(String toolName, String error, long duration) {
        return new McpToolCallResponse(toolName, false, Map.of(), error, duration);
    }
}
