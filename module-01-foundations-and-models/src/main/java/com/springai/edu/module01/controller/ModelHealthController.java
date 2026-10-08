package com.springai.edu.module01.controller;

import com.springai.edu.module01.dto.ChatPromptRequest;
import com.springai.edu.module01.dto.ModelHealthStatus;
import com.springai.edu.module01.dto.MultiModelComparisonResult;
import com.springai.edu.module01.service.MultiModelComparisonService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller exposing model health checks and concurrent multi-model comparisons.
 */
@RestController
@RequestMapping("/api/v1/models")
public class ModelHealthController {

    private final MultiModelComparisonService comparisonService;

    public ModelHealthController(MultiModelComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

    /**
     * Probes all configured model providers.
     */
    @GetMapping("/health")
    public List<ModelHealthStatus> checkHealth() {
        return comparisonService.checkHealth();
    }

    /**
     * Executes prompt concurrently against all active models using Virtual Threads.
     */
    @PostMapping(value = "/compare", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MultiModelComparisonResult compareModels(@Valid @RequestBody ChatPromptRequest request) {
        return comparisonService.compareModels(request);
    }
}
