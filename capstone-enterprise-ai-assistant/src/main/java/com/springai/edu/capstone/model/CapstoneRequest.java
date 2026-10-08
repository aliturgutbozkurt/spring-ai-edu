package com.springai.edu.capstone.model;

import jakarta.validation.constraints.NotBlank;

public record CapstoneRequest(
        @NotBlank(message = "Query must not be blank")
        String query,
        String userId,
        String categoryHint
) {}
