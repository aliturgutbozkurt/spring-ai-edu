package com.springai.edu.module09.model;

import jakarta.validation.constraints.NotBlank;
import java.util.Map;

public record McpToolCallRequest(
        @NotBlank(message = "Tool name is required")
        String toolName,
        Map<String, Object> arguments
) {}
