package com.springai.edu.module11.model;

import jakarta.validation.constraints.NotBlank;

public record SecurePromptRequest(
        @NotBlank(message = "Prompt cannot be blank")
        String prompt,
        String userId
) {}
