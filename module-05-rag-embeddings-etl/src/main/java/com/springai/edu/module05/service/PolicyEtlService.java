package com.springai.edu.module05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PolicyEtlService {

    private static final Logger log = LoggerFactory.getLogger(PolicyEtlService.class);

    private final VectorStore vectorStore;
    private final TokenTextSplitter tokenTextSplitter;

    public PolicyEtlService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.tokenTextSplitter = new TokenTextSplitter();
    }

    public List<Document> chunkAndIngest(String rawContent, String documentName, String category) {
        log.info("Starting ETL ingestion for document: '{}', category: '{}'", documentName, category);

        Document initialDoc = new Document(rawContent, Map.of(
                "source", documentName,
                "category", category,
                "ingestedAt", System.currentTimeMillis()
        ));

        List<Document> chunks = tokenTextSplitter.apply(List.of(initialDoc));
        log.info("Document '{}' split into {} chunks. Ingesting into VectorStore...", documentName, chunks.size());

        vectorStore.accept(chunks);
        log.info("Successfully ingested {} chunks into VectorStore.", chunks.size());

        return chunks;
    }
}
