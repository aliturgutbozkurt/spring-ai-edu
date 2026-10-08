package com.springai.edu.module02.controller;

import com.springai.edu.module02.dto.SqlGenerationRequest;
import com.springai.edu.module02.dto.SqlGenerationResponse;
import com.springai.edu.module02.service.SqlGeneratorService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller exposing endpoints for prompt template evaluation and SQL generation.
 */
@RestController
@RequestMapping("/api/v1/prompts")
public class PromptController {

    private final SqlGeneratorService sqlGeneratorService;

    public PromptController(SqlGeneratorService sqlGeneratorService) {
        this.sqlGeneratorService = sqlGeneratorService;
    }

    @PostMapping(value = "/sql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SqlGenerationResponse generateSql(@Valid @RequestBody SqlGenerationRequest request) {
        return sqlGeneratorService.generateSql(request);
    }
}
