package com.springai.edu.module01.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request payload for submitting prompts to the Spring AI ChatClient.
 * Uses Java 25/27 record for immutable state.
 *
 * @param prompt        The user input prompt
 * @param systemMessage Optional system instructions overriding the default
 * @param modelProvider Model provider selector ("ollama", "mock", "openai")
 * @param temperature   Sampling temperature (0.0 to 1.0)
 */
public record ChatPromptRequest(
        @NotBlank(message = "Prompt cannot be blank")
        String prompt,
        String systemMessage,
        String modelProvider,
        Double temperature
) {
    public ChatPromptRequest(String prompt) {
        this(prompt, null, null, null);
    }
}
