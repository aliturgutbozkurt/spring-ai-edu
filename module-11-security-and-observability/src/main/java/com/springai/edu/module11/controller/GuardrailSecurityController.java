package com.springai.edu.module11.controller;

import com.springai.edu.module11.guardrail.PiiSanitizationService;
import com.springai.edu.module11.guardrail.PromptInjectionDetector;
import com.springai.edu.module11.model.SecurePromptRequest;
import com.springai.edu.module11.model.SecurePromptResponse;
import com.springai.edu.module11.telemetry.AiMetricsRecorder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security")
public class GuardrailSecurityController {

    private final PiiSanitizationService piiService;
    private final PromptInjectionDetector injectionDetector;
    private final AiMetricsRecorder metricsRecorder;

    public GuardrailSecurityController(
            PiiSanitizationService piiService,
            PromptInjectionDetector injectionDetector,
            AiMetricsRecorder metricsRecorder
    ) {
        this.piiService = piiService;
        this.injectionDetector = injectionDetector;
        this.metricsRecorder = metricsRecorder;
    }

    @PostMapping("/secure-chat")
    public ResponseEntity<SecurePromptResponse> executeSecureChat(@Valid @RequestBody SecurePromptRequest request) {
        long start = System.currentTimeMillis();
        metricsRecorder.recordRequest();

        // 1. Prompt Injection Gate
        if (injectionDetector.isInjectionAttempt(request.prompt())) {
            metricsRecorder.recordInjectionBlocked();
            long latency = System.currentTimeMillis() - start;
            return ResponseEntity.badRequest().body(new SecurePromptResponse(
                    request.prompt(),
                    "SECURITY_ALERT: Prompt injection attempt detected and blocked.",
                    false,
                    java.util.List.of(),
                    true,
                    latency
            ));
        }

        // 2. PII Sanitization Gate
        var piiResult = piiService.sanitize(request.prompt());
        if (piiResult.piiFound()) {
            metricsRecorder.recordPiiRedacted(piiResult.detectedTypes().size());
        }

        long latency = System.currentTimeMillis() - start;
        metricsRecorder.recordInferenceDuration(latency);

        String safeResponse = "Enterprise Assistant: Processed prompt securely. Result generated without data exposure.";
        return ResponseEntity.ok(new SecurePromptResponse(
                piiResult.sanitizedText(),
                safeResponse,
                piiResult.piiFound(),
                piiResult.detectedTypes(),
                false,
                latency
        ));
    }
}
