package com.springai.edu.capstone.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OperationsToolSubagent {

    private static final Logger log = LoggerFactory.getLogger(OperationsToolSubagent.class);

    public String executeOperation(String query) {
        log.info("OperationsToolSubagent executing action for: {}", query);

        String lower = query.toLowerCase();
        if (lower.contains("ticket") || lower.contains("incident")) {
            String ticketId = "INC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            return "Operations Subagent: Successfully created support ticket " + ticketId + " with High priority.";
        } else if (lower.contains("status") || lower.contains("server")) {
            return "Operations Subagent: All 12 enterprise microservices are HEALTHY. Latency: 42ms.";
        } else if (lower.contains("password") || lower.contains("reset")) {
            return "Operations Subagent: Automated password reset link dispatched via secure SSO webhook.";
        } else {
            return "Operations Subagent: Executed system command successfully with exit status OK.";
        }
    }
}
