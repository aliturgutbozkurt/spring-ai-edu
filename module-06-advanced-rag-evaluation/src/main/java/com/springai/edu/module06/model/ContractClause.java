package com.springai.edu.module06.model;

public record ContractClause(
        String clauseId,
        String contractId,
        String clauseType,
        String jurisdiction,
        int effectiveYear,
        String text,
        double relevanceScore
) {}
