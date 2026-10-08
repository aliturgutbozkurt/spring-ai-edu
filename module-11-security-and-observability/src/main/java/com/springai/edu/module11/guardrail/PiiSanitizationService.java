package com.springai.edu.module11.guardrail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PiiSanitizationService {

    private static final Logger log = LoggerFactory.getLogger(PiiSanitizationService.class);

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("\\b(?:\\d{4}[- ]?){3}\\d{4}\\b");
    private static final Pattern SSN_PATTERN = Pattern.compile("\\b\\d{3}-\\d{2}-\\d{4}\\b");

    public record SanitizationResult(String sanitizedText, boolean piiFound, List<String> detectedTypes) {}

    public SanitizationResult sanitize(String input) {
        if (input == null || input.isBlank()) {
            return new SanitizationResult("", false, List.of());
        }

        String result = input;
        List<String> types = new ArrayList<>();

        if (EMAIL_PATTERN.matcher(result).find()) {
            types.add("EMAIL");
            result = EMAIL_PATTERN.matcher(result).replaceAll("[REDACTED_EMAIL]");
        }

        if (CREDIT_CARD_PATTERN.matcher(result).find()) {
            types.add("CREDIT_CARD");
            result = CREDIT_CARD_PATTERN.matcher(result).replaceAll("[REDACTED_CREDIT_CARD]");
        }

        if (SSN_PATTERN.matcher(result).find()) {
            types.add("SSN");
            result = SSN_PATTERN.matcher(result).replaceAll("[REDACTED_SSN]");
        }

        boolean found = !types.isEmpty();
        if (found) {
            log.warn("PII detected and redacted: {}", types);
        }

        return new SanitizationResult(result, found, types);
    }
}
