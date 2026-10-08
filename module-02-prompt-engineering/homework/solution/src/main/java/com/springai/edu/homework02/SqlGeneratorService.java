package com.springai.edu.homework02;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.Map;

/**
 * HOMEWORK 02 (REFERENCE SOLUTION): Prompt Engineering & SQL Generation
 */
public class SqlGeneratorService {

    private final ChatClient chatClient;

    public record GenerationResult(String sql, boolean rejected) {}

    public SqlGeneratorService(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    public GenerationResult generateSqlQuery(String schema, String userQuestion) {
        if (userQuestion == null || userQuestion.toLowerCase().contains("ignore previous instructions")) {
            return new GenerationResult("REJECTED", true);
        }

        PromptTemplate template = new PromptTemplate(
                "You are an expert SQL translator. Given schema: {schema}\nTranslate question: {question}"
        );

        String prompt = template.render(Map.of(
                "schema", schema != null ? schema : "",
                "question", userQuestion
        ));

        String response = chatClient.prompt().user(prompt).call().content();
        return new GenerationResult(response, false);
    }
}
