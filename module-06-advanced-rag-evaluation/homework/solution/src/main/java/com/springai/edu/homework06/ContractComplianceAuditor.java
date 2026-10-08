package com.springai.edu.homework06;

import java.util.Arrays;
import java.util.List;

public class ContractComplianceAuditor {

    public double auditCompliance(String clauseText, String requiredStandardKeyword) {
        if (clauseText == null || requiredStandardKeyword == null) {
            return 0.0;
        }
        String lowerClause = clauseText.toLowerCase();
        String lowerKeyword = requiredStandardKeyword.toLowerCase();

        if (lowerClause.contains(lowerKeyword)) {
            return 1.0;
        }
        return 0.0;
    }

    public double computeGroundedness(String contextText, String generatedAnswer) {
        if (contextText == null || generatedAnswer == null || generatedAnswer.isBlank()) {
            return 0.0;
        }

        List<String> answerWords = Arrays.stream(generatedAnswer.toLowerCase().split("\\W+"))
                .filter(w -> w.length() > 3)
                .toList();

        if (answerWords.isEmpty()) {
            return 1.0;
        }

        String lowerContext = contextText.toLowerCase();
        long matches = answerWords.stream().filter(lowerContext::contains).count();
        return (double) matches / answerWords.size();
    }
}
