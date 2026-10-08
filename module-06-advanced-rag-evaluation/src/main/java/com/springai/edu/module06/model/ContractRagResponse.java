package com.springai.edu.module06.model;

import java.util.List;

public record ContractRagResponse(
        String query,
        String answer,
        List<ContractClause> rerankedClauses,
        RagEvaluationResult evaluationResult,
        long executionTimeMs
) {}
