package com.springai.edu.homework05;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;

public class PolicyIngestionBot {

    private final VectorStore vectorStore;
    private final TokenTextSplitter splitter;

    public PolicyIngestionBot(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.splitter = new TokenTextSplitter();
    }

    public List<Document> ingestPolicy(String policyText, String department, String documentId) {
        // TODO: Create a Spring AI Document containing metadata tags 'department' and 'documentId'.
        // TODO: Split the document using this.splitter.
        // TODO: Ingest the resulting chunks into this.vectorStore.
        throw new UnsupportedOperationException("TODO: Implement ingestPolicy");
    }

    public List<Document> searchByDepartment(String query, String department, int topK) {
        // TODO: Build a SearchRequest with query, topK, and a metadata filterExpression matching department.
        // TODO: Perform similaritySearch against this.vectorStore and return results.
        throw new UnsupportedOperationException("TODO: Implement searchByDepartment with metadata filtering");
    }
}
