package com.springai.edu.module02.config;

import com.springai.edu.common.mock.MockChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

@Configuration
public class PromptConfig {

    @Value("classpath:prompts/sql-generator-system.st")
    private Resource sqlSystemPromptResource;

    @Bean(name = "mockChatModel")
    @Primary
    public MockChatModel mockChatModel() {
        MockChatModel mock = new MockChatModel(prompt ->
                "SELECT * FROM customers WHERE active = true;\nExplanation: Fetches all active customers from the database."
        );
        mock.addCannedResponse("top 5 customers",
                "SELECT c.id, c.name, SUM(o.total_amount) FROM customers c JOIN orders o ON c.id = o.customer_id GROUP BY c.id, c.name ORDER BY 3 DESC LIMIT 5;\nExplanation: Retrieves the top five customers ranked by spending.");
        mock.addCannedResponse("injection",
                "SECURITY_REJECTED: Adversarial instructions detected in prompt input.");
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

    public Resource getSqlSystemPromptResource() {
        return sqlSystemPromptResource;
    }
}
