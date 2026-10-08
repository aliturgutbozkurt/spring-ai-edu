package com.springai.edu.module09.model;

import java.util.Map;

public record McpToolDescriptor(
        String name,
        String description,
        Map<String, Object> inputSchema
) {}
