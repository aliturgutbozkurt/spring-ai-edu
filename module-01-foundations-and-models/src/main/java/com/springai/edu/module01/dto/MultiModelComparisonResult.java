package com.springai.edu.module01.dto;

import java.util.List;

/**
 * Aggregated result of running a single prompt concurrently across multiple models.
 *
 * @param prompt          The prompt submitted to the models
 * @param responses       The individual responses collected from each active provider
 * @param totalDurationMs Wall-clock duration of the concurrent execution in milliseconds
 */
public record MultiModelComparisonResult(
        String prompt,
        List<ChatPromptResponse> responses,
        long totalDurationMs
) {}
