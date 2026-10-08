package com.springai.edu.module07.config;

import com.springai.edu.common.mock.MockChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultimodalConfig {

    @Bean
    @ConditionalOnMissingBean(ChatModel.class)
    public ChatModel chatModel() {
        return new MockChatModel("""
                {
                  "supplierName": "Industrial Fasteners Corp",
                  "receiptNumber": "REC-9942",
                  "totalAmount": 482.50,
                  "items": [
                    {"sku": "BOLT-M8", "description": "Stainless Steel M8 Bolts (Box of 100)", "quantity": 5, "unitPrice": 42.50},
                    {"sku": "NUT-M8", "description": "Hex Flange Nuts M8 (Box of 100)", "quantity": 10, "unitPrice": 27.00}
                  ],
                  "discrepanciesDetected": false,
                  "confidenceScore": 0.98
                }
                """);
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
}
