package com.springai.edu.module08.service;

import com.springai.edu.module08.model.TutorChatRequest;
import com.springai.edu.module08.model.TutorChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class TutorConversationService {

    private static final Logger log = LoggerFactory.getLogger(TutorConversationService.class);

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public TutorConversationService(ChatClient chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient;
        this.chatMemory = chatMemory;
    }

    public TutorChatResponse chat(TutorChatRequest request) {
        long start = System.currentTimeMillis();
        String convId = request.conversationId();

        log.info("Processing chat turn for conversationId: '{}', message: '{}'", convId, request.message());

        String reply = chatClient.prompt()
                .user(request.message())
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, convId))
                .call()
                .content();

        int historySize = chatMemory.get(convId, 100).size();
        long duration = System.currentTimeMillis() - start;

        log.info("Chat turn completed in {}ms. Memory now holds {} messages for session '{}'",
                duration, historySize, convId);

        return new TutorChatResponse(convId, reply, historySize, duration);
    }

    public void resetConversation(String conversationId) {
        log.info("Clearing memory for conversationId: '{}'", conversationId);
        chatMemory.clear(conversationId);
    }
}
