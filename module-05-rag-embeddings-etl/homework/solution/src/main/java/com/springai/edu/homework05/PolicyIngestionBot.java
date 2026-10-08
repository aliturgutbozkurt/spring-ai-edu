package com.springai.edu.homework05;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;
import java.util.Map;

public class PolicyIngestionBot {

    private final VectorStore vectorStore;
    private final TokenTextSplitter splitter;

    public PolicyIngestionBot(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.splitter = new TokenTextSplitter();
    }

    public List<Document> ingestPolicy(String policyText, String department, String documentId) {
        Document doc = new Document(policyText, Map.of(
                "department", department,
                "documentId", documentId
        ));

        List<Document> chunks = splitter.apply(List.of(doc));
        vectorStore.accept(chunks);
        return chunks;
    }

    public List<Document> searchByDepartment(String query, String department, int topK) {
        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(topK)
                .filterExpression("department == '" + department + "'")
                .build();

        return vectorStore.similaritySearch(request);
    }
}
