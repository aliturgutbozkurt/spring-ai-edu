package com.springai.edu.homework06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContractComplianceAuditorTest {

    private final ContractComplianceAuditor auditor = new ContractComplianceAuditor();

    @Test
    @DisplayName("Should pass compliance when required regulatory clause is present")
    void shouldAuditCompliance() {
        String clause = "Vendor must comply with GDPR and ISO 27001 security controls at all times.";
        double gdprScore = auditor.auditCompliance(clause, "GDPR");
        double socScore = auditor.auditCompliance(clause, "SOC2");

        assertThat(gdprScore).isEqualTo(1.0);
        assertThat(socScore).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Should evaluate groundedness between context and generated summary")
    void shouldComputeGroundedness() {
        String context = "Payment terms are net 30 days from invoice receipt date.";
        String accurateAnswer = "The invoice payment terms require settlement within 30 days.";

        double groundedness = auditor.computeGroundedness(context, accurateAnswer);
        assertThat(groundedness).isGreaterThanOrEqualTo(0.50);
    }
}
