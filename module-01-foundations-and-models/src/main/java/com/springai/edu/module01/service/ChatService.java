package com.springai.edu.module01.service;

import com.springai.edu.module01.dto.ChatPromptRequest;
import com.springai.edu.module01.dto.ChatPromptResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Service demonstrating synchronous and streaming interactions using Spring AI's fluent ChatClient.
 */
@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final ChatClient defaultChatClient;
    private final ModelRoutingService modelRoutingService;

    public ChatService(ChatClient defaultChatClient, ModelRoutingService modelRoutingService) {
        this.defaultChatClient = defaultChatClient;
        this.modelRoutingService = modelRoutingService;
    }

    /**
     * Executes a synchronous chat prompt and returns structured metrics.
     */
    public ChatPromptResponse generateResponse(ChatPromptRequest request) {
        long startTime = System.currentTimeMillis();

        ChatClient clientToUse = resolveChatClient(request.modelProvider());

        var promptSpec = clientToUse.prompt().user(request.prompt());
        if (request.systemMessage() != null && !request.systemMessage().isBlank()) {
            promptSpec.system(request.systemMessage());
        }

        ChatResponse response = promptSpec.call().chatResponse();
        long duration = System.currentTimeMillis() - startTime;

        String content = response != null && response.getResult() != null
                ? response.getResult().getOutput().getText()
                : "No response received";

        String modelUsed = request.modelProvider() != null && !request.modelProvider().isBlank()
                ? request.modelProvider()
                : "default";

        long promptTokens = 0;
        long generationTokens = 0;
        if (response != null && response.getMetadata() != null && response.getMetadata().getUsage() != null) {
            promptTokens = response.getMetadata().getUsage().getPromptTokens();
            generationTokens = response.getMetadata().getUsage().getCompletionTokens();
        }

        log.info("Prompt generated via [{}] in {}ms (tokens: prompt={}, gen={})",
                modelUsed, duration, promptTokens, generationTokens);

        return new ChatPromptResponse(content, modelUsed, promptTokens, generationTokens, duration);
    }

    /**
     * Streams responses reactively using Flux<String> and Spring AI's streaming client.
     */
    public Flux<String> streamResponse(String prompt, String provider) {
        ChatClient clientToUse = resolveChatClient(provider);
        log.info("Initiating streaming chat for provider [{}]", provider != null ? provider : "default");

        return clientToUse.prompt()
                .user(prompt)
                .stream()
                .content();
    }

    private ChatClient resolveChatClient(String providerName) {
        if (providerName == null || providerName.isBlank()) {
            return defaultChatClient;
        }
        ChatModel targetModel = modelRoutingService.getModel(providerName);
        return ChatClient.builder(targetModel).build();
    }
}
