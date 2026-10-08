package com.springai.edu.module06.model;

/**
 * Represents the RAG Triad evaluation metrics:
 * 1. Context Relevance: Are the retrieved documents relevant to the user query?
 * 2. Groundedness (Faithfulness): Is the LLM's response strictly supported by the context?
 * 3. Answer Relevance: Does the generated answer address the user's initial query?
 */
public record RagEvaluationResult(
        double contextRelevanceScore,
        double groundednessScore,
        double answerRelevanceScore,
        boolean passedQualityGate,
        String reasoningSummary
) {
    public static final double PASSING_THRESHOLD = 0.70;

    public static RagEvaluationResult evaluate(double contextRel, double groundedness, double answerRel, String summary) {
        boolean pass = contextRel >= PASSING_THRESHOLD && groundedness >= PASSING_THRESHOLD && answerRel >= PASSING_THRESHOLD;
        return new RagEvaluationResult(contextRel, groundedness, answerRel, pass, summary);
    }
}
