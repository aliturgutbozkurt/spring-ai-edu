package com.springai.edu.module01.service;

import com.springai.edu.module01.dto.ChatPromptRequest;
import com.springai.edu.module01.dto.ChatPromptResponse;
import com.springai.edu.module01.dto.ModelHealthStatus;
import com.springai.edu.module01.dto.MultiModelComparisonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executes prompts concurrently across all registered model providers using Java 25/27 Virtual Threads.
 * Demonstrates high-throughput model benchmarking and graceful failure isolation.
 */
@Service
public class MultiModelComparisonService {

    private static final Logger log = LoggerFactory.getLogger(MultiModelComparisonService.class);

    private final ModelRoutingService modelRoutingService;
    private final ExecutorService virtualThreadExecutor;

    public MultiModelComparisonService(ModelRoutingService modelRoutingService) {
        this.modelRoutingService = modelRoutingService;
        this.virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
    }

    /**
     * Executes prompt across all available models concurrently on Virtual Threads.
     */
    public MultiModelComparisonResult compareModels(ChatPromptRequest request) {
        long overallStart = System.currentTimeMillis();
        Map<String, ChatModel> models = modelRoutingService.getAllAvailableModels();

        log.info("Starting concurrent multi-model comparison on Virtual Threads for {} providers", models.size());

        List<CompletableFuture<ChatPromptResponse>> futures = models.entrySet().stream()
                .map(entry -> CompletableFuture.supplyAsync(
                        () -> executeSingleModel(entry.getKey(), entry.getValue(), request),
                        virtualThreadExecutor
                ))
                .toList();

        // Await all tasks with safety timeout
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .orTimeout(30, TimeUnit.SECONDS)
                .join();

        List<ChatPromptResponse> responses = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        long totalDuration = System.currentTimeMillis() - overallStart;
        log.info("Multi-model comparison completed in {}ms across {} models", totalDuration, responses.size());

        return new MultiModelComparisonResult(request.prompt(), responses, totalDuration);
    }

    private ChatPromptResponse executeSingleModel(String providerName, ChatModel model, ChatPromptRequest request) {
        long start = System.currentTimeMillis();
        try {
            ChatClient client = ChatClient.builder(model).build();
            var spec = client.prompt().user(request.prompt());
            if (request.systemMessage() != null && !request.systemMessage().isBlank()) {
                spec.system(request.systemMessage());
            }

            ChatResponse response = spec.call().chatResponse();
            long duration = System.currentTimeMillis() - start;

            String content = response != null && response.getResult() != null
                    ? response.getResult().getOutput().getText()
                    : "No response";

            long promptTokens = 0;
            long genTokens = 0;
            if (response != null && response.getMetadata() != null && response.getMetadata().getUsage() != null) {
                promptTokens = response.getMetadata().getUsage().getPromptTokens();
                genTokens = response.getMetadata().getUsage().getCompletionTokens();
            }

            return new ChatPromptResponse(content, providerName, promptTokens, genTokens, duration);
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            log.error("Failed to query model provider [{}]: {}", providerName, e.getMessage());
            return new ChatPromptResponse(
                    "Error querying provider: " + e.getMessage(),
                    providerName,
                    0,
                    0,
                    duration
            );
        }
    }

    /**
     * Probes the health and responsiveness of all registered model providers.
     */
    public List<ModelHealthStatus> checkHealth() {
        Map<String, ChatModel> models = modelRoutingService.getAllAvailableModels();
        List<ModelHealthStatus> statuses = new ArrayList<>();

        models.forEach((name, model) -> {
            long start = System.currentTimeMillis();
            try {
                ChatClient client = ChatClient.builder(model).build();
                String testReply = client.prompt().user("ping").call().content();
                long latency = System.currentTimeMillis() - start;
                statuses.add(new ModelHealthStatus(name, true, latency, "Responsive. Sample output: " + testReply));
            } catch (Exception e) {
                long latency = System.currentTimeMillis() - start;
                statuses.add(new ModelHealthStatus(name, false, latency, "Offline or Error: " + e.getMessage()));
            }
        });

        return statuses;
    }
}
