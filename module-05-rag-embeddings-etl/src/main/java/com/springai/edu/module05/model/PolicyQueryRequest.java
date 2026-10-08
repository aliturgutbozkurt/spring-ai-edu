package com.springai.edu.module05.model;

import jakarta.validation.constraints.NotBlank;

public record PolicyQueryRequest(
        @NotBlank(message = "Query must not be blank")
        String query,
        int topK,
        double similarityThreshold
) {
    public PolicyQueryRequest {
        if (topK <= 0) {
            topK = 3;
        }
        if (similarityThreshold <= 0.0) {
            similarityThreshold = 0.5;
        }
    }
}
