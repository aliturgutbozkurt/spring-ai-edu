package com.springai.edu.module06;

import com.springai.edu.module06.model.RagEvaluationResult;
import com.springai.edu.module06.service.RagTriadEvaluatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RagTriadEvaluatorServiceTest {

    private final RagTriadEvaluatorService evaluatorService = new RagTriadEvaluatorService();

    @Test
    @DisplayName("Should pass quality gate when context, answer, and query are strongly aligned")
    void shouldPassQualityGateForAlignedTriad() {
        String query = "What is the termination notice period?";
        String context = "Section 5.2 Termination: The party terminating must provide thirty days written notice.";
        String answer = "The required termination notice period is thirty days written notice under section 5.2.";

        RagEvaluationResult result = evaluatorService.evaluateTriad(query, context, answer);

        assertThat(result.contextRelevanceScore()).isGreaterThanOrEqualTo(0.70);
        assertThat(result.groundednessScore()).isGreaterThanOrEqualTo(0.70);
        assertThat(result.answerRelevanceScore()).isGreaterThanOrEqualTo(0.70);
        assertThat(result.passedQualityGate()).isTrue();
    }
}
