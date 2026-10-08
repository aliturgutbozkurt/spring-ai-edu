package com.springai.edu.module08;

import com.springai.edu.module08.advisor.TokenWindowAdvisor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TokenWindowAdvisorTest {

    @Test
    @DisplayName("Should prune old messages when history exceeds max window limit")
    void shouldPruneExcessMessages() {
        ChatMemory chatMemory = new InMemoryChatMemory();
        String convId = "sess-prune";

        // Seed with 6 messages
        chatMemory.add(convId, List.of(
                new UserMessage("Q1"), new AssistantMessage("A1"),
                new UserMessage("Q2"), new AssistantMessage("A2"),
                new UserMessage("Q3"), new AssistantMessage("A3")
        ));

        assertThat(chatMemory.get(convId, 100)).hasSize(6);

        TokenWindowAdvisor advisor = new TokenWindowAdvisor(chatMemory, 4);
        AdvisedRequest request = AdvisedRequest.builder()
                .chatModel(new com.springai.edu.common.mock.MockChatModel("test"))
                .userText("dummy")
                .adviseContext(Map.of("chat_memory_conversation_id", convId))
                .build();

        advisor.aroundCall(request, mock(CallAroundAdvisorChain.class));

        // After pruning to max 4 messages
        assertThat(chatMemory.get(convId, 100)).hasSize(4);
    }
}
