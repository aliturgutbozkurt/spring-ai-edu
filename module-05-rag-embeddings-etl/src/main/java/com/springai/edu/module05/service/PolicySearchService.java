package com.springai.edu.module05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicySearchService {

    private static final Logger log = LoggerFactory.getLogger(PolicySearchService.class);

    private final VectorStore vectorStore;

    public PolicySearchService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> search(String query, int topK, double similarityThreshold) {
        log.info("Executing semantic search for query='{}' (topK={}, threshold={})", query, topK, similarityThreshold);

        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(topK)
                .similarityThreshold(similarityThreshold)
                .build();

        List<Document> results = vectorStore.similaritySearch(request);
        log.info("Found {} matching documents for query='{}'", results.size(), query);
        return results;
    }
}
