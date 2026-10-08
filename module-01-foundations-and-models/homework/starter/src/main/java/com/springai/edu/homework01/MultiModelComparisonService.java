package com.springai.edu.homework01;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * HOMEWORK 01 (STARTER): Multi-Model Concurrency Service
 *
 * Instructions for Student:
 * 1. Implement compareAcrossModels using Java 25/27 Virtual Threads.
 * 2. Ensure each model call is isolated: if a model throws an Exception, record the error
 *    in ProviderResult instead of letting the entire batch fail.
 * 3. Run 'mvn test' to verify your solution against the automated grading suite.
 */
public class MultiModelComparisonService {

    private final Map<String, ChatModel> models;
    private final ExecutorService virtualThreadExecutor;

    public record ProviderResult(String providerName, String output, long durationMs, boolean success) {}
    public record BatchComparisonSummary(String prompt, List<ProviderResult> results, long totalWallClockMs) {}

    public MultiModelComparisonService(Map<String, ChatModel> models) {
        this.models = models;
        this.virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
    }

    /**
     * Executes the given prompt concurrently across all models registered in this service.
     *
     * @param prompt The user prompt to send
     * @return Aggregated results including latency and status
     */
    public BatchComparisonSummary compareAcrossModels(String prompt) {
        // TODO: Step 1 - Record the overall start time using System.currentTimeMillis()
        // TODO: Step 2 - Concurrently invoke queryModel for each entry in 'models' using virtualThreadExecutor
        // TODO: Step 3 - Wait for all virtual thread tasks to finish
        // TODO: Step 4 - Return new BatchComparisonSummary(prompt, results, totalWallClockMs)

        throw new UnsupportedOperationException("TODO: Implement compareAcrossModels using Virtual Threads");
    }

    ProviderResult queryModel(String providerName, ChatModel model, String prompt) {
        long start = System.currentTimeMillis();
        try {
            ChatClient client = ChatClient.builder(model).build();
            String response = client.prompt().user(prompt).call().content();
            long duration = System.currentTimeMillis() - start;
            return new ProviderResult(providerName, response, duration, true);
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            return new ProviderResult(providerName, "Error: " + e.getMessage(), duration, false);
        }
    }
}
