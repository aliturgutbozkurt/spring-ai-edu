package com.springai.edu.module05.service;

import com.springai.edu.module05.model.PolicyAnswerResponse;
import com.springai.edu.module05.model.PolicyQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PolicyQaService {

    private static final Logger log = LoggerFactory.getLogger(PolicyQaService.class);

    private static final String RAG_PROMPT_TEMPLATE = """
            You are an authoritative enterprise HR and compliance policy assistant.
            Answer the user's question accurately using ONLY the provided context snippets below.
            If the answer cannot be found in the context, respond with "Information not found in enterprise policies."
            
            [POLICY CONTEXT]
            {context}
            
            [USER QUESTION]
            {question}
            """;

    private final PolicySearchService searchService;
    private final ChatClient chatClient;

    public PolicyQaService(PolicySearchService searchService, ChatClient chatClient) {
        this.searchService = searchService;
        this.chatClient = chatClient;
    }

    public PolicyAnswerResponse answerQuestion(PolicyQueryRequest request) {
        long start = System.currentTimeMillis();
        List<Document> matchedDocs = searchService.search(
                request.query(),
                request.topK(),
                request.similarityThreshold()
        );

        String context = matchedDocs.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n---\n"));

        List<String> snippets = matchedDocs.stream()
                .map(Document::getText)
                .toList();

        PromptTemplate promptTemplate = new PromptTemplate(RAG_PROMPT_TEMPLATE);
        String renderedPrompt = promptTemplate.render(Map.of(
                "context", context.isBlank() ? "No relevant policy documents found." : context,
                "question", request.query()
        ));

        String answer = chatClient.prompt()
                .user(renderedPrompt)
                .call()
                .content();

        long latency = System.currentTimeMillis() - start;
        log.info("Answered policy query in {}ms with {} retrieved chunks", latency, matchedDocs.size());

        return new PolicyAnswerResponse(
                request.query(),
                answer,
                snippets,
                matchedDocs.size(),
                latency
        );
    }
}
