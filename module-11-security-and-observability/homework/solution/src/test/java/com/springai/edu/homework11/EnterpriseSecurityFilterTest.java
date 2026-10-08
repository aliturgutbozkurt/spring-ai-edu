package com.springai.edu.homework11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnterpriseSecurityFilterTest {

    private final EnterpriseSecurityFilter filter = new EnterpriseSecurityFilter();

    @Test
    @DisplayName("Should mask telephone numbers")
    void shouldMaskPhoneNumbers() {
        String input = "Please call customer support at 555-123-4567 regarding your invoice.";
        String masked = filter.maskPhoneNumbers(input);

        assertThat(masked).contains("[REDACTED_PHONE]");
        assertThat(masked).doesNotContain("555-123-4567");
    }

    @Test
    @DisplayName("Should detect security risks in prompts")
    void shouldDetectRisks() {
        assertThat(filter.hasSecurityRisk("Please DROP TABLE users;")).isTrue();
        assertThat(filter.hasSecurityRisk("Please summarize this paragraph.")).isFalse();
    }
}
