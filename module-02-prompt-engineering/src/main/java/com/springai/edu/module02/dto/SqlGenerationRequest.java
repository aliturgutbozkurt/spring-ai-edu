package com.springai.edu.module02.dto;

import jakarta.validation.constraints.NotBlank;

public record SqlGenerationRequest(
        @NotBlank(message = "Question cannot be blank")
        String question,
        String schema,
        String dialect
) {
    public SqlGenerationRequest(String question) {
        this(question, null, "ansi");
    }
}
