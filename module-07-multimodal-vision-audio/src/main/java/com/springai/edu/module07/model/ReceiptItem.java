package com.springai.edu.module07.model;

public record ReceiptItem(
        String sku,
        String description,
        int quantity,
        double unitPrice
) {}
