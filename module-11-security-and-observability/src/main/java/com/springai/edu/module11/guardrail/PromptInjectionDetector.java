package com.springai.edu.module11.guardrail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptInjectionDetector {

    private static final Logger log = LoggerFactory.getLogger(PromptInjectionDetector.class);

    private static final List<String> INJECTION_PATTERNS = List.of(
            "ignore previous instructions",
            "disregard all earlier instructions",
            "you are now in jailbreak mode",
            "act as dan",
            "bypass ethical filters",
            "system prompt leak"
    );

    public boolean isInjectionAttempt(String prompt) {
        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        String lower = prompt.toLowerCase();
        for (String pattern : INJECTION_PATTERNS) {
            if (lower.contains(pattern)) {
                log.warn("Security Alert: Prompt injection pattern detected: '{}'", pattern);
                return true;
            }
        }
        return false;
    }
}
