package com.springai.edu.homework01;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * HOMEWORK 01 (REFERENCE SOLUTION): Multi-Model Concurrency Service
 *
 * Implements concurrent query execution using Java 25/27 Virtual Threads,
 * complete error isolation, and metrics collection.
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
     * Executes the prompt concurrently across all registered models on Virtual Threads.
     */
    public BatchComparisonSummary compareAcrossModels(String prompt) {
        long start = System.currentTimeMillis();

        List<CompletableFuture<ProviderResult>> futures = models.entrySet().stream()
                .map(entry -> CompletableFuture.supplyAsync(
                        () -> queryModel(entry.getKey(), entry.getValue(), prompt),
                        virtualThreadExecutor
                ))
                .toList();

        // Wait for all virtual thread tasks with safety timeout
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .orTimeout(30, TimeUnit.SECONDS)
                .join();

        List<ProviderResult> results = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        long totalDuration = System.currentTimeMillis() - start;
        return new BatchComparisonSummary(prompt, results, totalDuration);
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
