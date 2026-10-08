package com.springai.edu.module01.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fluent ChatClient configuration with default system prompt and logging advisor.
 */
@Configuration
public class ChatClientConfig {

    private static final Logger log = LoggerFactory.getLogger(ChatClientConfig.class);

    public static final String DEFAULT_SYSTEM_PROMPT =
            "You are an expert Spring AI Assistant built with modern Java 25/27 and Spring Boot. " +
            "Provide concise, accurate, and type-safe explanations and code.";

    @Bean
    @ConditionalOnMissingBean
    public ChatClient.Builder chatClientBuilder(ChatModel chatModel) {
        log.info("Creating ChatClient.Builder from primary ChatModel: {}", chatModel.getClass().getSimpleName());
        return ChatClient.builder(chatModel);
    }

    @Bean
    public ChatClient defaultChatClient(ChatClient.Builder chatClientBuilder) {
        log.info("Building default fluent ChatClient with system instructions and SimpleLoggerAdvisor");
        return chatClientBuilder
                .defaultSystem(DEFAULT_SYSTEM_PROMPT)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
