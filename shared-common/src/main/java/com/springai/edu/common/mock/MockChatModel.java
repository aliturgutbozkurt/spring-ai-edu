package com.springai.edu.common.mock;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Deterministic MockChatModel for offline testing and automated grading.
 * Allows tests to run instantly without external API keys or network latency.
 */
public class MockChatModel implements ChatModel {

    private final Function<Prompt, String> responseGenerator;
    private final ConcurrentHashMap<String, String> cannedResponses = new ConcurrentHashMap<>();

    public MockChatModel() {
        this(prompt -> "Mock response for: " + prompt.getContents());
    }

    public MockChatModel(String fixedResponse) {
        this(prompt -> fixedResponse);
    }

    public MockChatModel(Function<Prompt, String> responseGenerator) {
        this.responseGenerator = responseGenerator;
    }

    public void addCannedResponse(String querySubstring, String response) {
        cannedResponses.put(querySubstring.toLowerCase(), response);
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        String query = prompt.getContents().toLowerCase();
        String answer = cannedResponses.entrySet().stream()
                .filter(entry -> query.contains(entry.getKey()))
                .map(java.util.Map.Entry::getValue)
                .findFirst()
                .orElseGet(() -> responseGenerator.apply(prompt));

        Generation generation = new Generation(new AssistantMessage(answer));
        return new ChatResponse(List.of(generation));
    }

    @Override
    public reactor.core.publisher.Flux<ChatResponse> stream(Prompt prompt) {
        ChatResponse fullResponse = call(prompt);
        String text = fullResponse.getResult().getOutput().getText();
        String[] words = text.split(" ");

        return reactor.core.publisher.Flux.fromArray(words)
                .map(word -> new ChatResponse(List.of(new Generation(new AssistantMessage(word + " ")))));
    }

    @Override
    public ChatOptions getDefaultOptions() {
        return null;
    }
}
