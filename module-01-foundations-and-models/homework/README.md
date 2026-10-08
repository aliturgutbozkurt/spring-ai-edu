# Homework 01: Multi-Model Health & Comparison Service (English)

## Objective
Build a resilient, high-concurrency Spring AI service that can query multiple LLM providers concurrently using Java 25/27 Virtual Threads, aggregate performance metrics (latency, token consumption), and handle provider timeouts gracefully.

---

## Architecture Diagram
```
Client Request
      │
      ▼
MultiModelComparisonService
      │
      ├──> [Virtual Thread 1] ──> OllamaChatModel  (llama3.2)
      ├──> [Virtual Thread 2] ──> OpenAiChatModel  (gpt-4o-mini)
      └──> [Virtual Thread 3] ──> MockChatModel    (Deterministic Fallback)
      │
      ▼
MultiModelComparisonResult (aggregated latencies & token counts)
```

---

## Requirements

1. **Virtual Thread Execution**:
   - Must use `Executors.newVirtualThreadPerTaskExecutor()` or `CompletableFuture` configured with a virtual thread executor.
   - Tasks must execute in parallel without blocking standard platform thread pools.

2. **Error Isolation**:
   - If one model provider fails or throws a connection timeout, the service must **not** fail the entire request.
   - The failed provider's response must indicate the error message while the other models complete normally.

3. **Metrics Aggregation**:
   - Measure execution latency in milliseconds for each provider.
   - Extract `promptTokens` and `generationTokens` from `ChatResponse.getMetadata().getUsage()`.

---

## Directory Structure
- `starter/`: Contains stub methods marked with `// TODO` and failing unit tests.
- `solution/`: Reference implementation passing 100% of tests.

## Running Tests
```bash
# In starter/ directory:
mvn test

# In solution/ directory:
mvn test
```

## Grading Rubric (100 Points Total)
- **30 Pts**: Concurrent execution on Virtual Threads.
- **25 Pts**: Graceful degradation when a provider throws an exception.
- **25 Pts**: Accurate extraction of latency and token metrics.
- **20 Pts**: Clean code, Java record usage, and adherence to Spring AI idioms.
