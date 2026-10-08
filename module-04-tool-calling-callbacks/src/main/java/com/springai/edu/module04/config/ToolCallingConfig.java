package com.springai.edu.module04.config;

import com.springai.edu.common.mock.MockChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ToolCallingConfig {

    @Bean(name = "mockChatModel")
    @Primary
    public MockChatModel mockChatModel() {
        MockChatModel mock = new MockChatModel(prompt ->
                "I have verified order ORD-101. The order has been delivered and your refund request of $149.99 has been approved."
        );
        mock.addCannedResponse("escalat",
                "Your request has been escalated to a Tier-2 supervisor. Reference ticket ID recorded.");
        mock.addCannedResponse("status",
                "Order ORD-102 is currently in transit and scheduled for delivery within 2 business days.");
        return mock;
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatClient.Builder chatClientBuilder(ChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
