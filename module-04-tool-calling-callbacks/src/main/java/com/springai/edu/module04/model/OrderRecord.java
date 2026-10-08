package com.springai.edu.module04.model;

public record OrderRecord(
        String orderId,
        String customerName,
        double amount,
        String status,
        String orderDate
) {}
