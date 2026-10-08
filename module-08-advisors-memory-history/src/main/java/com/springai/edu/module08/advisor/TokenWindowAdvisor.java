package com.springai.edu.module08.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.core.Ordered;

import java.util.List;

public class TokenWindowAdvisor implements CallAroundAdvisor, Ordered {

    private static final Logger log = LoggerFactory.getLogger(TokenWindowAdvisor.class);

    private final ChatMemory chatMemory;
    private final int maxMessages;

    public TokenWindowAdvisor(ChatMemory chatMemory, int maxMessages) {
        this.chatMemory = chatMemory;
        this.maxMessages = maxMessages;
    }

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        String conversationId = (String) advisedRequest.adviseContext().getOrDefault("chat_memory_conversation_id", "default");
        
        List<Message> history = chatMemory.get(conversationId, 100);
        if (history.size() > maxMessages) {
            log.info("Pruning conversational memory for session '{}': size {} exceeds window {}",
                    conversationId, history.size(), maxMessages);
            // Retain the last maxMessages
            List<Message> retained = history.subList(history.size() - maxMessages, history.size());
            chatMemory.clear(conversationId);
            chatMemory.add(conversationId, retained);
        }

        return chain.nextAroundCall(advisedRequest);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String getName() {
        return "TokenWindowAdvisor";
    }
}
