package com.springai.edu.module04.model;

public record TicketTriageResult(
        String ticketId,
        String customerQuery,
        String resolution,
        boolean escalated,
        long durationMs
) {}
