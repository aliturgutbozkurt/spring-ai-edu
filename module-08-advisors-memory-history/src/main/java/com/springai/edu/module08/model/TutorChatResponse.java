package com.springai.edu.module08.model;

public record TutorChatResponse(
        String conversationId,
        String response,
        int totalMessagesInMemory,
        long latencyMs
) {}
