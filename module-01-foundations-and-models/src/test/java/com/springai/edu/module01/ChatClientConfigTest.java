package com.springai.edu.module01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
        "spring.ai.ollama.chat.enabled=false"
})
class ChatClientConfigTest {

    @Autowired
    private ChatClient chatClient;

    @Test
    @DisplayName("Should initialize fluent ChatClient with default configuration")
    void shouldInitializeFluentChatClient() {
        assertNotNull(chatClient, "ChatClient bean must be configured in ApplicationContext");

        // Execute deterministic call against MockChatModel
        String response = chatClient.prompt()
                .user("hello")
                .call()
                .content();

        assertNotNull(response);
        assertTrue(response.contains("Hello from Spring AI and Java 25/27!"),
                "Should return canned greeting response from MockChatModel");
    }
}
