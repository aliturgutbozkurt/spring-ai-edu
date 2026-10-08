package com.springai.edu.module01.config;

import com.springai.edu.common.mock.MockChatModel;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Registers model providers into the Spring context.
 * Guarantees zero-cost offline development with MockChatModel as a deterministic fallback.
 */
@Configuration
public class ModelRoutingConfig {

    @Bean(name = "mockChatModel")
    @Primary
    public MockChatModel mockChatModel() {
        MockChatModel mock = new MockChatModel(prompt ->
                "Processed by MockChatModel: " + prompt.getContents()
        );
        mock.addCannedResponse("hello", "Hello from Spring AI and Java 25/27!");
        mock.addCannedResponse("ping", "pong");
        mock.addCannedResponse("health", "OK: Mock provider is fully operational.");
        return mock;
    }
}
