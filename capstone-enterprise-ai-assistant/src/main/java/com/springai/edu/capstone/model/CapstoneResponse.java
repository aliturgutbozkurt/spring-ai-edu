package com.springai.edu.capstone.model;

import java.util.List;
import java.util.Map;

public record CapstoneResponse(
        String answer,
        String delegatedAgent,
        List<String> auditTrail,
        Map<String, Object> metadata
) {}
