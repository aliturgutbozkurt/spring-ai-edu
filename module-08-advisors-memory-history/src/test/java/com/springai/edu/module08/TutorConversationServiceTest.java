package com.springai.edu.module08;

import com.springai.edu.common.mock.MockChatModel;
import com.springai.edu.module08.model.TutorChatRequest;
import com.springai.edu.module08.model.TutorChatResponse;
import com.springai.edu.module08.service.TutorConversationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;

import static org.assertj.core.api.Assertions.assertThat;

class TutorConversationServiceTest {

    private TutorConversationService tutorService;
    private ChatMemory chatMemory;

    @BeforeEach
    void setUp() {
        chatMemory = new InMemoryChatMemory();
        ChatClient chatClient = ChatClient.builder(new MockChatModel("Excellent question! Virtual Threads are managed by the JVM."))
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
        tutorService = new TutorConversationService(chatClient, chatMemory);
    }

    @Test
    @DisplayName("Should maintain conversation history across multiple turns for same conversationId")
    void shouldMaintainConversationHistory() {
        String convId = "session-student-42";

        TutorChatResponse turn1 = tutorService.chat(new TutorChatRequest(convId, "What are Java Virtual Threads?", "Java"));
        assertThat(turn1.totalMessagesInMemory()).isGreaterThanOrEqualTo(2); // user + assistant

        TutorChatResponse turn2 = tutorService.chat(new TutorChatRequest(convId, "How do they differ from OS threads?", "Java"));
        assertThat(turn2.totalMessagesInMemory()).isGreaterThanOrEqualTo(4);

        // Verify isolation with a different session
        TutorChatResponse turnOther = tutorService.chat(new TutorChatRequest("session-other-99", "Hello", "Java"));
        assertThat(turnOther.totalMessagesInMemory()).isEqualTo(2);
    }
}
