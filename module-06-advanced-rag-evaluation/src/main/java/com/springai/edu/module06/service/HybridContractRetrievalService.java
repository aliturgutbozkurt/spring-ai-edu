package com.springai.edu.module06.service;

import com.springai.edu.module06.model.ContractClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HybridContractRetrievalService {

    private static final Logger log = LoggerFactory.getLogger(HybridContractRetrievalService.class);

    private final VectorStore vectorStore;

    public HybridContractRetrievalService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void indexClause(ContractClause clause) {
        Document doc = new Document(clause.text(), Map.of(
                "clauseId", clause.clauseId(),
                "contractId", clause.contractId(),
                "clauseType", clause.clauseType(),
                "jurisdiction", clause.jurisdiction(),
                "effectiveYear", clause.effectiveYear()
        ));
        vectorStore.accept(List.of(doc));
    }

    public List<Document> retrieveClauses(String query, String jurisdiction, int topK) {
        log.info("Retrieving contract clauses: query='{}', jurisdiction='{}'", query, jurisdiction);

        SearchRequest.Builder builder = SearchRequest.builder()
                .query(query)
                .topK(topK);

        if (jurisdiction != null && !jurisdiction.isBlank()) {
            builder.filterExpression("jurisdiction == '" + jurisdiction + "'");
        }

        return vectorStore.similaritySearch(builder.build());
    }
}
