package com.springai.edu.capstone.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class KnowledgeRagSubagent {

    private static final Logger log = LoggerFactory.getLogger(KnowledgeRagSubagent.class);

    private final ChatModel chatModel;

    // Simulated enterprise knowledge base
    private final Map<String, String> enterpriseCorpus = Map.of(
            "vacation", "Enterprise Vacation Policy: Full-time employees receive 25 days paid leave annually.",
            "remote", "Remote Work Policy: Hybrid work allows up to 3 days remote work per week.",
            "sla", "Service Level Agreement: Severity 1 incidents must be acknowledged within 15 minutes."
    );

    public KnowledgeRagSubagent(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String retrieveAndAnswer(String query) {
        log.info("KnowledgeRagSubagent processing enterprise query: {}", query);

        String lowerQuery = query.toLowerCase();
        String context = enterpriseCorpus.entrySet().stream()
                .filter(entry -> lowerQuery.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("Enterprise Standard Operational Procedure: Consult your direct manager for unindexed requests.");

        String promptText = "Context:\n" + context + "\n\nUser Question:\n" + query + "\n\nAnswer using only the given context:";
        return chatModel.call(new Prompt(promptText)).getResult().getOutput().getText();
    }
}
