package com.springai.edu.module06.service;

import com.springai.edu.module06.model.RagEvaluationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RagTriadEvaluatorService {

    private static final Logger log = LoggerFactory.getLogger(RagTriadEvaluatorService.class);

    public RagEvaluationResult evaluateTriad(String query, String context, String answer) {
        log.info("Computing automated RAG Triad evaluation metrics");

        double contextRelevance = calculateContextRelevance(query, context);
        double groundedness = calculateGroundedness(context, answer);
        double answerRelevance = calculateAnswerRelevance(query, answer);

        String summary = String.format("ContextRel=%.2f, Groundedness=%.2f, AnswerRel=%.2f",
                contextRelevance, groundedness, answerRelevance);

        return RagEvaluationResult.evaluate(contextRelevance, groundedness, answerRelevance, summary);
    }

    private double calculateContextRelevance(String query, String context) {
        if (query.isBlank() || context.isBlank()) return 0.0;
        List<String> queryTokens = Arrays.stream(query.toLowerCase().split("\\W+"))
                .filter(s -> s.length() > 2)
                .toList();

        if (queryTokens.isEmpty()) return 1.0;
        long matches = queryTokens.stream().filter(t -> context.toLowerCase().contains(t)).count();
        return Math.min(1.0, (double) matches / queryTokens.size() + 0.3);
    }

    private double calculateGroundedness(String context, String answer) {
        if (context.isBlank() || answer.isBlank()) return 0.0;
        List<String> answerTokens = Arrays.stream(answer.toLowerCase().split("\\W+"))
                .filter(s -> s.length() > 3)
                .toList();

        if (answerTokens.isEmpty()) return 1.0;
        long matches = answerTokens.stream().filter(t -> context.toLowerCase().contains(t)).count();
        return Math.min(1.0, (double) matches / answerTokens.size() + 0.35);
    }

    private double calculateAnswerRelevance(String query, String answer) {
        if (query.isBlank() || answer.isBlank()) return 0.0;
        List<String> queryTokens = Arrays.stream(query.toLowerCase().split("\\W+"))
                .filter(s -> s.length() > 3)
                .toList();

        if (queryTokens.isEmpty()) return 1.0;
        long matches = queryTokens.stream().filter(t -> answer.toLowerCase().contains(t)).count();
        return Math.min(1.0, (double) matches / queryTokens.size() + 0.4);
    }
}
