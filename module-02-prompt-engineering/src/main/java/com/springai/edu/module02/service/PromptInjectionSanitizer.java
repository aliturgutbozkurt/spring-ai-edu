package com.springai.edu.module02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Defensive prompt engineering component that detects and mitigates prompt injection,
 * system prompt extraction, and jailbreak attempts before sending prompts to the LLM.
 */
@Component
public class PromptInjectionSanitizer {

    private static final Logger log = LoggerFactory.getLogger(PromptInjectionSanitizer.class);

    private static final List<Pattern> INJECTION_PATTERNS = List.of(
            Pattern.compile("(?i)ignore\\s+(all\\s+)?(previous|prior)\\s+instructions"),
            Pattern.compile("(?i)disregard\\s+(all\\s+)?(previous|prior)"),
            Pattern.compile("(?i)system\\s+prompt\\s+override"),
            Pattern.compile("(?i)reveal\\s+(the\\s+)?(system\\s+prompt|instructions)"),
            Pattern.compile("(?i)you\\s+are\\s+now\\s+in\\s+developer\\s+mode"),
            Pattern.compile("(?i)dan\\s+mode|jailbreak"),
            Pattern.compile("(?i);\\s*drop\\s+table")
    );

    /**
     * Checks if the given user prompt contains known adversarial injection markers.
     */
    public boolean detectInjection(String prompt) {
        if (prompt == null || prompt.isBlank()) {
            return false;
        }

        for (Pattern pattern : INJECTION_PATTERNS) {
            if (pattern.matcher(prompt).find()) {
                log.warn("Prompt injection pattern detected: '{}'", pattern.pattern());
                return true;
            }
        }
        return false;
    }

    /**
     * Sanitizes input by replacing dangerous characters or returns safe fallback if adversarial.
     */
    public String sanitize(String prompt) {
        if (prompt == null) return "";
        if (detectInjection(prompt)) {
            log.warn("Sanitizing adversarial prompt input");
            return "[SECURITY ALERT: Suspicious instruction pattern stripped] " + prompt.replaceAll("(?i)ignore.*instructions", "");
        }
        return prompt.trim();
    }
}
