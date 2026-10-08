package com.springai.edu.homework03;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.converter.BeanOutputConverter;

import java.util.List;

/**
 * HOMEWORK 03 (REFERENCE SOLUTION): Structured Output Extraction
 */
public class ResumeParserService {

    public record ExtractedCandidate(
            @JsonPropertyDescription("Candidate full name") String name,
            @JsonPropertyDescription("Primary email") String email,
            @JsonPropertyDescription("Years of software experience") int experienceYears,
            @JsonPropertyDescription("List of primary programming languages") List<String> languages
    ) {}

    private final ChatClient chatClient;
    private final BeanOutputConverter<ExtractedCandidate> converter;

    public ResumeParserService(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
        this.converter = new BeanOutputConverter<>(ExtractedCandidate.class);
    }

    public ExtractedCandidate extractCandidate(String text) {
        String format = converter.getFormat();
        String response = chatClient.prompt()
                .user(text + "\n" + format)
                .call()
                .content();

        return converter.convert(response);
    }
}
