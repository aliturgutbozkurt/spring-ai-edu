package com.springai.edu.homework03;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.converter.BeanOutputConverter;

import java.util.List;

/**
 * HOMEWORK 03 (STARTER): Structured Output Extraction
 *
 * Instructions for Student:
 * 1. Implement extractCandidate using BeanOutputConverter<ExtractedCandidate>.
 * 2. Pass converter.getFormat() in the prompt instructions to guide the model.
 * 3. Convert the response and return the ExtractedCandidate record.
 * 4. Run 'mvn test' to verify.
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
        // TODO: Step 1 - Obtain format instructions using converter.getFormat()
        // TODO: Step 2 - Call chatClient.prompt().user(text + "\n" + formatInstructions).call().content()
        // TODO: Step 3 - Parse output with converter.convert(response) and return

        throw new UnsupportedOperationException("TODO: Implement extractCandidate using BeanOutputConverter");
    }
}
