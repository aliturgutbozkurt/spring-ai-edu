package com.springai.edu.module06.controller;

import com.springai.edu.module06.model.ContractClause;
import com.springai.edu.module06.model.ContractRagResponse;
import com.springai.edu.module06.model.RagEvaluationResult;
import com.springai.edu.module06.service.ContextualRerankingService;
import com.springai.edu.module06.service.HybridContractRetrievalService;
import com.springai.edu.module06.service.RagTriadEvaluatorService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contracts")
public class ContractRagController {

    private final HybridContractRetrievalService retrievalService;
    private final ContextualRerankingService rerankingService;
    private final RagTriadEvaluatorService evaluatorService;
    private final ChatClient chatClient;

    public ContractRagController(
            HybridContractRetrievalService retrievalService,
            ContextualRerankingService rerankingService,
            RagTriadEvaluatorService evaluatorService,
            ChatClient chatClient
    ) {
        this.retrievalService = retrievalService;
        this.rerankingService = rerankingService;
        this.evaluatorService = evaluatorService;
        this.chatClient = chatClient;
    }

    @PostMapping("/query")
    public ResponseEntity<ContractRagResponse> queryContract(
            @RequestParam String query,
            @RequestParam(required = false, defaultValue = "") String jurisdiction,
            @RequestParam(defaultValue = "5") int topK
    ) {
        long start = System.currentTimeMillis();

        List<Document> rawMatches = retrievalService.retrieveClauses(query, jurisdiction, topK);
        List<ContractClause> reranked = rerankingService.rerank(query, rawMatches);

        String context = reranked.stream()
                .map(c -> String.format("[%s - %s] %s", c.clauseId(), c.clauseType(), c.text()))
                .collect(Collectors.joining("\n"));

        String answer = chatClient.prompt()
                .user("Analyze the legal contract clauses:\n" + context + "\nQuestion: " + query)
                .call()
                .content();

        RagEvaluationResult eval = evaluatorService.evaluateTriad(query, context, answer);
        long duration = System.currentTimeMillis() - start;

        return ResponseEntity.ok(new ContractRagResponse(query, answer, reranked, eval, duration));
    }

    @PostMapping("/clauses")
    public ResponseEntity<Map<String, String>> addClause(@RequestBody ContractClause clause) {
        retrievalService.indexClause(clause);
        return ResponseEntity.ok(Map.of("status", "INDEXED", "clauseId", clause.clauseId()));
    }
}
