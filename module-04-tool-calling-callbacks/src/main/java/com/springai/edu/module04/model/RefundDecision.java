package com.springai.edu.module04.model;

public record RefundDecision(
        String orderId,
        boolean approved,
        double refundAmount,
        String reason
) {}
