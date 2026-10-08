package com.springai.edu.module05.controller;

import com.springai.edu.module05.model.PolicyAnswerResponse;
import com.springai.edu.module05.model.PolicyQueryRequest;
import com.springai.edu.module05.service.PolicyEtlService;
import com.springai.edu.module05.service.PolicyQaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/policies")
public class PolicyQaController {

    private final PolicyQaService qaService;
    private final PolicyEtlService etlService;

    public PolicyQaController(PolicyQaService qaService, PolicyEtlService etlService) {
        this.qaService = qaService;
        this.etlService = etlService;
    }

    @PostMapping("/query")
    public ResponseEntity<PolicyAnswerResponse> queryPolicy(@Valid @RequestBody PolicyQueryRequest request) {
        PolicyAnswerResponse response = qaService.answerQuestion(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/ingest")
    public ResponseEntity<Map<String, Object>> ingestDocument(@RequestBody Map<String, String> payload) {
        String title = payload.getOrDefault("title", "policy.md");
        String category = payload.getOrDefault("category", "General");
        String content = payload.getOrDefault("content", "");

        var chunks = etlService.chunkAndIngest(content, title, category);
        return ResponseEntity.ok(Map.of(
                "status", "SUCCESS",
                "chunksIngested", chunks.size(),
                "title", title
        ));
    }
}
