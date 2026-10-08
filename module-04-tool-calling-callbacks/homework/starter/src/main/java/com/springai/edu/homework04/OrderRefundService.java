package com.springai.edu.homework04;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.annotation.Tool;

/**
 * HOMEWORK 04 (STARTER): Tool Calling & Customer Refund Service
 *
 * Instructions for Student:
 * 1. Annotate checkEligibility with @Tool(description = "...").
 * 2. In processRefundRequest, register this service's tools with chatClient.
 * 3. Run 'mvn test' to verify your solution.
 */
public class OrderRefundService {

    private final ChatClient chatClient;

    public OrderRefundService(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    // TODO: Step 1 - Add @Tool annotation with a clear description
    public String checkEligibility(String orderId, double amount) {
        // TODO: Step 2 - Return "ELIGIBLE" if amount <= 200.0, else "EXCEEDS_LIMIT"
        throw new UnsupportedOperationException("TODO: Implement checkEligibility tool method");
    }

    public String processRefundRequest(String orderId, double amount, String userPrompt) {
        // TODO: Step 3 - Call chatClient.prompt().tools(this).user(userPrompt).call().content()
        throw new UnsupportedOperationException("TODO: Implement processRefundRequest using tools");
    }
}
