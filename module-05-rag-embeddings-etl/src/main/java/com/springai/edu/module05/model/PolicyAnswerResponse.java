package com.springai.edu.module05.model;

import java.util.List;

public record PolicyAnswerResponse(
        String query,
        String answer,
        List<String> sourceSnippets,
        int retrievedChunksCount,
        long latencyMs
) {}
