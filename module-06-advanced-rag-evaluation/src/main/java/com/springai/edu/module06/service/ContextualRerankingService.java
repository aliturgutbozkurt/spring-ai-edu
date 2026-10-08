package com.springai.edu.module06.service;

import com.springai.edu.module06.model.ContractClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ContextualRerankingService {

    private static final Logger log = LoggerFactory.getLogger(ContextualRerankingService.class);

    /**
     * Reranks retrieved candidate chunks based on intent alignment and term overlap.
     */
    public List<ContractClause> rerank(String query, List<Document> candidates) {
        log.info("Reranking {} candidate chunks against query: '{}'", candidates.size(), query);

        String[] queryKeywords = query.toLowerCase().split("\\W+");

        return candidates.stream()
                .map(doc -> {
                    String text = doc.getText();
                    String textLower = text.toLowerCase();

                    // Calculate keyword match density
                    double matchCount = 0;
                    for (String kw : queryKeywords) {
                        if (!kw.isBlank() && textLower.contains(kw)) {
                            matchCount += 1.0;
                        }
                    }

                    double score = queryKeywords.length > 0 ? (matchCount / queryKeywords.length) : 0.5;

                    // Boost if clause type is specified
                    String clauseType = String.valueOf(doc.getMetadata().getOrDefault("clauseType", "GENERAL"));
                    if (textLower.contains("terminate") || textLower.contains("liability") || textLower.contains("indemnif")) {
                        score = Math.min(1.0, score + 0.2);
                    }

                    return new ContractClause(
                            String.valueOf(doc.getMetadata().getOrDefault("clauseId", "UNKNOWN")),
                            String.valueOf(doc.getMetadata().getOrDefault("contractId", "MSA-DEFAULT")),
                            clauseType,
                            String.valueOf(doc.getMetadata().getOrDefault("jurisdiction", "GLOBAL")),
                            doc.getMetadata().get("effectiveYear") instanceof Number n ? n.intValue() : 2026,
                            text,
                            score
                    );
                })
                .sorted(Comparator.comparingDouble(ContractClause::relevanceScore).reversed())
                .toList();
    }
}
