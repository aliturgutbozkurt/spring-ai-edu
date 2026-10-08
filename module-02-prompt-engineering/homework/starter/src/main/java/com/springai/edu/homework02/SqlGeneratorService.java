package com.springai.edu.homework02;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.Map;

/**
 * HOMEWORK 02 (STARTER): Prompt Engineering & SQL Generation
 *
 * Instructions for Student:
 * 1. Implement generateSqlQuery using PromptTemplate and ChatClient.
 * 2. Guard against prompt injection: if the prompt contains "ignore previous instructions", return "REJECTED".
 * 3. Run 'mvn test' to verify your solution.
 */
public class SqlGeneratorService {

    private final ChatClient chatClient;

    public record GenerationResult(String sql, boolean rejected) {}

    public SqlGeneratorService(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    public GenerationResult generateSqlQuery(String schema, String userQuestion) {
        // TODO: Step 1 - Check if userQuestion contains prompt injection ("ignore previous instructions")
        // TODO: Step 2 - If injection detected, return new GenerationResult("REJECTED", true)
        // TODO: Step 3 - Create a PromptTemplate combining schema and userQuestion
        // TODO: Step 4 - Call chatClient and return new GenerationResult(output, false)

        throw new UnsupportedOperationException("TODO: Implement generateSqlQuery with PromptTemplate and injection guard");
    }
}
