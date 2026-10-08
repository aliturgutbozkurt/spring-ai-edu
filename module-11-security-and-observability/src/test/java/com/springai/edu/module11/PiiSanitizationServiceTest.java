package com.springai.edu.module11;

import com.springai.edu.module11.guardrail.PiiSanitizationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PiiSanitizationServiceTest {

    private final PiiSanitizationService sanitizer = new PiiSanitizationService();

    @Test
    @DisplayName("Should detect and redact emails, credit cards, and SSNs from prompt")
    void shouldSanitizePii() {
        String sensitivePrompt = "Customer john.doe@example.com paid using card 4532-1234-5678-9012 and SSN 123-45-6789.";
        var result = sanitizer.sanitize(sensitivePrompt);

        assertThat(result.piiFound()).isTrue();
        assertThat(result.detectedTypes()).contains("EMAIL", "CREDIT_CARD", "SSN");
        assertThat(result.sanitizedText())
                .contains("[REDACTED_EMAIL]")
                .contains("[REDACTED_CREDIT_CARD]")
                .contains("[REDACTED_SSN]")
                .doesNotContain("john.doe@example.com")
                .doesNotContain("4532-1234-5678-9012");
    }
}
