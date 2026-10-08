package com.springai.edu.homework04;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.annotation.Tool;

/**
 * HOMEWORK 04 (REFERENCE SOLUTION): Tool Calling & Customer Refund Service
 */
public class OrderRefundService {

    private final ChatClient chatClient;

    public OrderRefundService(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    @Tool(description = "Check whether an order ID is eligible for refund up to a maximum allowed policy threshold")
    public String checkEligibility(String orderId, double amount) {
        return amount <= 200.0 ? "ELIGIBLE" : "EXCEEDS_LIMIT";
    }

    public String processRefundRequest(String orderId, double amount, String userPrompt) {
        return chatClient.prompt()
                .tools(this)
                .user(userPrompt)
                .call()
                .content();
    }
}
