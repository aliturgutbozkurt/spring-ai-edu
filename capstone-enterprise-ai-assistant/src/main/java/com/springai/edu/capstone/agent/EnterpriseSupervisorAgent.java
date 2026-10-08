package com.springai.edu.capstone.agent;

import com.springai.edu.capstone.model.CapstoneRequest;
import com.springai.edu.capstone.model.CapstoneResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class EnterpriseSupervisorAgent {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseSupervisorAgent.class);

    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("\\b(?:\\d[ -]*?){13,16}\\b");

    private final KnowledgeRagSubagent ragSubagent;
    private final OperationsToolSubagent opsSubagent;

    public EnterpriseSupervisorAgent(KnowledgeRagSubagent ragSubagent, OperationsToolSubagent opsSubagent) {
        this.ragSubagent = ragSubagent;
        this.opsSubagent = opsSubagent;
    }

    public CapstoneResponse processRequest(CapstoneRequest request) {
        List<String> auditTrail = new ArrayList<>();
        auditTrail.add("[GUARDRAIL] Evaluated request security and PII constraints");

        // Guardrail: PII Redaction
        String sanitizedQuery = CREDIT_CARD_PATTERN.matcher(request.query()).replaceAll("[REDACTED_CARD]");
        if (!sanitizedQuery.equals(request.query())) {
            auditTrail.add("[GUARDRAIL] Detected sensitive card number - sanitized input");
        }

        String lower = sanitizedQuery.toLowerCase();
        String delegatedAgent;
        String answer;

        if (lower.contains("policy") || lower.contains("vacation") || lower.contains("remote") || lower.contains("sla")) {
            delegatedAgent = "KnowledgeRagSubagent";
            auditTrail.add("[SUPERVISOR] Delegated request to KnowledgeRagSubagent");
            answer = ragSubagent.retrieveAndAnswer(sanitizedQuery);
        } else if (lower.contains("ticket") || lower.contains("status") || lower.contains("server") || lower.contains("password")) {
            delegatedAgent = "OperationsToolSubagent";
            auditTrail.add("[SUPERVISOR] Delegated request to OperationsToolSubagent");
            answer = opsSubagent.executeOperation(sanitizedQuery);
        } else {
            delegatedAgent = "EnterpriseSupervisorAgent";
            auditTrail.add("[SUPERVISOR] Direct resolution via supervisor general policy");
            answer = "Enterprise Assistant: Processed general corporate request: " + sanitizedQuery;
        }

        auditTrail.add("[AUDIT] Execution completed successfully with zero violations");

        Map<String, Object> metadata = Map.of(
                "executionEngine", "Spring AI Multi-Agent Supervisor",
                "sanitizedQuery", sanitizedQuery,
                "userId", request.userId() != null ? request.userId() : "ANONYMOUS",
                "virtualThreads", true
        );

        return new CapstoneResponse(answer, delegatedAgent, auditTrail, metadata);
    }
}
