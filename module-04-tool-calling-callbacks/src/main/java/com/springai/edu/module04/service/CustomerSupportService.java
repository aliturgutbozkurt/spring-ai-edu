package com.springai.edu.module04.service;

import com.springai.edu.module04.model.TicketTriageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Autonomous Customer Support service orchestrating LLM tool calling.
 */
@Service
public class CustomerSupportService {

    private static final Logger log = LoggerFactory.getLogger(CustomerSupportService.class);

    private final ChatClient chatClient;
    private final SupportTools supportTools;

    public CustomerSupportService(ChatClient chatClient, SupportTools supportTools) {
        this.chatClient = chatClient;
        this.supportTools = supportTools;
    }

    public TicketTriageResult triageTicket(String customerInquiry) {
        long startTime = System.currentTimeMillis();
        String ticketId = "TCK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        log.info("Processing support ticket [{}] for inquiry: '{}'", ticketId, customerInquiry);

        String systemPrompt = """
                You are an autonomous customer support triage specialist.
                You have access to tools for looking up orders, calculating refunds, and escalating tickets.
                Always use the getOrderDetails tool when an order ID is mentioned.
                If the customer requests a refund and the order is delivered, invoke calculateRefund.
                If the customer is aggressive or asks for a supervisor, invoke escalateTicket.
                Provide a courteous and concise final response summarizing the outcome.
                """;

        String resolution = chatClient.prompt()
                .system(systemPrompt)
                .tools(supportTools)
                .user(customerInquiry)
                .call()
                .content();

        long duration = System.currentTimeMillis() - startTime;
        boolean escalated = resolution != null && resolution.toLowerCase().contains("escalat");

        log.info("Ticket [{}] resolved in {}ms (escalated: {})", ticketId, duration, escalated);

        return new TicketTriageResult(ticketId, customerInquiry, resolution, escalated, duration);
    }
}
