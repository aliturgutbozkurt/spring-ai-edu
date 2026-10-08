package com.springai.edu.module08.model;

import jakarta.validation.constraints.NotBlank;

public record TutorChatRequest(
        @NotBlank(message = "conversationId is mandatory")
        String conversationId,
        @NotBlank(message = "message must not be blank")
        String message,
        String studentSubject
) {}
