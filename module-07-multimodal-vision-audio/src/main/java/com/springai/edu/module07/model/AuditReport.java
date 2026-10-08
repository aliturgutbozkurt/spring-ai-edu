package com.springai.edu.module07.model;

import java.util.List;

public record AuditReport(
        String supplierName,
        String receiptNumber,
        double totalAmount,
        List<ReceiptItem> items,
        boolean discrepanciesDetected,
        double confidenceScore
) {}
