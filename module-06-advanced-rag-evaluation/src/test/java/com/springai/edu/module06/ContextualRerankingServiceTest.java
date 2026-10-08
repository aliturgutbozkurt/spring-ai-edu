package com.springai.edu.module06;

import com.springai.edu.module06.model.ContractClause;
import com.springai.edu.module06.service.ContextualRerankingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ContextualRerankingServiceTest {

    private final ContextualRerankingService rerankingService = new ContextualRerankingService();

    @Test
    @DisplayName("Should prioritize contract clauses with higher keyword match and critical clause types")
    void shouldPrioritizeRelevantClauses() {
        Document genericDoc = new Document("The parties agree to hold meetings every calendar quarter.",
                Map.of("clauseId", "C-01", "clauseType", "GOVERNANCE"));

        Document terminationDoc = new Document("Either party may terminate this agreement upon 30 days notice for material breach.",
                Map.of("clauseId", "C-02", "clauseType", "TERMINATION"));

        List<ContractClause> reranked = rerankingService.rerank(
                "Can we terminate for breach?",
                List.of(genericDoc, terminationDoc)
        );

        assertThat(reranked).hasSize(2);
        assertThat(reranked.get(0).clauseId()).isEqualTo("C-02");
        assertThat(reranked.get(0).relevanceScore()).isGreaterThan(reranked.get(1).relevanceScore());
    }
}
