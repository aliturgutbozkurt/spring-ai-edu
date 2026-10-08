package com.springai.edu.module04.service;

import com.springai.edu.module04.model.OrderRecord;
import com.springai.edu.module04.model.RefundDecision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service providing executable tools annotated with Spring AI's @Tool annotation.
 * These methods are dynamically exposed to LLMs via JSON Schema function definitions.
 */
@Service
public class SupportTools {

    private static final Logger log = LoggerFactory.getLogger(SupportTools.class);

    private final Map<String, OrderRecord> mockDatabase = new ConcurrentHashMap<>();

    public SupportTools() {
        mockDatabase.put("ORD-101", new OrderRecord("ORD-101", "John Doe", 149.99, "DELIVERED", "2026-03-01"));
        mockDatabase.put("ORD-102", new OrderRecord("ORD-102", "Jane Smith", 49.50, "IN_TRANSIT", "2026-03-05"));
        mockDatabase.put("ORD-103", new OrderRecord("ORD-103", "Bob Wilson", 599.00, "RETURNED", "2026-02-20"));
    }

    @Tool(description = "Lookup details of a customer order by order ID (e.g. ORD-101)")
    public OrderRecord getOrderDetails(String orderId) {
        log.info("Executing Tool: getOrderDetails for orderId='{}'", orderId);
        if (orderId == null) {
            return new OrderRecord("UNKNOWN", "Not Found", 0.0, "NOT_FOUND", "N/A");
        }
        return mockDatabase.getOrDefault(orderId.trim().toUpperCase(),
                new OrderRecord(orderId, "Unknown Customer", 0.0, "ORDER_NOT_FOUND", "N/A"));
    }

    @Tool(description = "Calculate refund eligibility for an order based on policy rules")
    public RefundDecision calculateRefund(String orderId, double requestedAmount, String reason) {
        log.info("Executing Tool: calculateRefund for orderId='{}', amount={}, reason='{}'",
                orderId, requestedAmount, reason);

        OrderRecord order = getOrderDetails(orderId);
        if ("ORDER_NOT_FOUND".equals(order.status())) {
            return new RefundDecision(orderId, false, 0.0, "Order does not exist");
        }

        if (requestedAmount > order.amount()) {
            return new RefundDecision(orderId, false, 0.0,
                    "Requested amount exceeds original order value of $" + order.amount());
        }

        if ("DELIVERED".equals(order.status()) || "RETURNED".equals(order.status())) {
            return new RefundDecision(orderId, true, requestedAmount,
                    "Refund approved under standard 30-day satisfaction guarantee for reason: " + reason);
        }

        return new RefundDecision(orderId, false, 0.0,
                "Cannot refund order in status: " + order.status() + ". Package is still in transit.");
    }

    @Tool(description = "Escalate ticket to human tier-2 supervisor")
    public String escalateTicket(String ticketId, String urgency) {
        log.warn("Executing Tool: escalateTicket ticketId='{}', urgency='{}'", ticketId, urgency);
        return "Ticket " + ticketId + " successfully escalated to Tier-2 supervisor with urgency: " + urgency;
    }
}
