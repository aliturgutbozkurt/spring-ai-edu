# Module 01 Specification: Foundations of Spring AI, Architecture & Model Providers

**Module ID**: `module-01-foundations-and-models`  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#2](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/2)  
**Status**: APPROVED (Phase 1: Specify)  
**Authors**: Spring AI Curriculum Engineering Team  
**Last Updated**: 2026-10-08  

---

## 1. Overview & Pedagogical Objectives

### 1.1 English Narrative
Module 01 lays the cornerstone for mastering generative AI applications in Java. Students transition from traditional REST services to AI-orchestrated architectures using Spring AI and modern Java 25/27. The core focus is introducing the Spring AI abstraction layer—specifically decoupling client application code from vendor-specific LLM APIs (OpenAI, Ollama, Anthropic, Gemini).

Key pedagogical goals:
1. **Understand Spring AI Abstractions**: Master the difference between low-level `ChatModel` and the modern fluent `ChatClient` introduced in Spring AI 1.x.
2. **Model Portability**: Implement zero-lock-in architectures where model providers can be swapped via configuration or dynamic runtime routing.
3. **Zero-Cost Local Development**: Learn how to develop, test, and debug locally using Ollama and Testcontainers without incurring API costs.
4. **Streaming & Asynchronous Interaction**: Build reactive streaming endpoints utilizing `Flux<String>` and Server-Sent Events (SSE) for low-latency perceived response times.
5. **Modern Java Idioms**: Utilize Java 25/27 records, pattern matching, and Virtual Threads (`spring.threads.virtual.enabled=true`) for high-concurrency model calls.

### 1.2 Türkçe Açıklama (Turkish Narrative)
Modül 01, Java ekosisteminde Üretken Yapay Zeka (Generative AI) uygulamaları geliştirmenin temellerini atar. Öğrenciler geleneksel REST mimarilerinden Spring AI ve modern Java 25/27 ile güçlendirilmiş yapay zeka orkestrasyonuna adım atar. Temel odak noktası, Spring AI soyutlama katmanıdır: Uygulama kodunun OpenAI, Ollama, Anthropic veya Gemini gibi spesifik LLM sağlayıcılarına olan bağımlılığını ortadan kaldırmak (Vendor Lock-in engelleme).

Temel pedagojik hedefler:
1. **Spring AI Soyutlamalarını Kavrama**: Düşük seviyeli `ChatModel` ile Spring AI 1.x ile gelen akıcı (fluent) `ChatClient` arasındaki farkı ve avantajları öğrenme.
2. **Model Taşınabilirliği (Model Portability)**: Sağlayıcıların yalnızca konfigürasyon değişikliği veya dinamik çalışma zamanı yönlendirmesiyle değiştirilebildiği mimariyi kurma.
3. **Sıfır Maliyetli Yerel Geliştirme**: Öğrencilerin hiçbir API anahtarı veya kredi kartı gerekmeden, Ollama ve Testcontainers ile yerel ortamda geliştirme ve test yapabilmesi.
4. **Akış (Streaming) ve Asenkron Yanıtlar**: Algılanan gecikme süresini (latency) düşürmek için `Flux<String>` ve Server-Sent Events (SSE) kullanarak gerçek zamanlı token akışı sağlama.
5. **Modern Java Pratikleri**: Yüksek eşzamanlılık gerektiren model çağrılarında Java 25/27 records, pattern matching ve Sanal İş Parçacıklarını (Virtual Threads - Project Loom) etkin kullanma.

---

## 2. Target Personas & Prerequisites

- **Audience**: Java Backend Developers, Spring Boot Engineers, Enterprise Architects.
- **Prerequisites**:
  - JDK 25 or 27 installed.
  - Basic familiarity with Spring Boot (`@Service`, `@RestController`, dependency injection).
  - Docker installed (for Ollama local model execution).

---

## 3. Functional Requirements

### FR-01: Fluent ChatClient Configuration
- The application MUST configure `ChatClient` using `ChatClient.builder(chatModel)` with default system prompts, default advisors, and default model options.
- The service MUST demonstrate:
  - Basic synchronous string generation: `chatClient.prompt().user(prompt).call().content()`
  - Entity extraction / typed response via `chatClient.prompt().user(prompt).call().chatResponse()`
  - Parameterized prompts with dynamic placeholder injection: `chatClient.prompt().user(u -> u.text("Hello {name}").param("name", name)).call().content()`

### FR-02: Multi-Model Injection & Dynamic Switching
- The system MUST register multiple `ChatModel` beans in the Spring ApplicationContext:
  - `ollamaChatModel` (primary local provider)
  - `mockChatModel` (fallback deterministic provider from `shared-common` for offline CI)
  - `openAiChatModel` (conditional cloud provider activated via `@Profile("openai")`)
- A router service `ModelRoutingService` MUST allow selecting the active model dynamically based on a query parameter or request header (`X-Model-Provider: ollama | mock | openai`).

### FR-03: Reactive Streaming via SSE (Server-Sent Events)
- A dedicated REST endpoint `GET /api/v1/chat/stream?prompt=...` MUST stream tokens as they arrive from the LLM using `Flux<String>` with `text/event-stream` media type.
- Per-chunk latency and streaming completion signals must be handled gracefully without blocking threads.

### FR-04: Virtual Thread Concurrency Benchmarking
- The system MUST demonstrate concurrent calls to models utilizing Java 25/27 Virtual Threads (`Executors.newVirtualThreadPerTaskExecutor()`).
- High-concurrency requests should run efficiently without exhausting system thread pools.

---

## 4. REST API & Data Schema Contracts

### 4.1 Chat Request & Response Records
```java
public record ChatPromptRequest(
    @NotBlank(message = "Prompt cannot be blank") String prompt,
    String systemMessage,
    String modelProvider,
    Double temperature
) {}

public record ChatPromptResponse(
    String content,
    String modelUsed,
    long promptTokens,
    long generationTokens,
    long durationMs
) {}

public record ModelHealthStatus(
    String providerName,
    boolean available,
    String latencyMs,
    String details
) {}

public record MultiModelComparisonResult(
    String prompt,
    List<ChatPromptResponse> responses,
    long totalDurationMs
) {}
```

### 4.2 Endpoints Specification
| Method | Endpoint | Description | Query / Body | Response Schema |
|---|---|---|---|---|
| `POST` | `/api/v1/chat` | Generate synchronous text | `ChatPromptRequest` | `ChatPromptResponse` |
| `GET` | `/api/v1/chat/stream` | Stream tokens via SSE | `prompt`, `provider` | `Flux<ServerSentEvent<String>>` |
| `GET` | `/api/v1/models/health` | Check availability of configured models | None | `List<ModelHealthStatus>` |
| `POST` | `/api/v1/models/compare` | Run prompt concurrently against all active models | `ChatPromptRequest` | `MultiModelComparisonResult` |

---

## 5. Non-Functional & Operational Requirements

1. **Zero-Cost First**: Running `mvn test` or launching the application with `spring.profiles.active=local` MUST succeed out-of-the-box using the deterministic mock model if Ollama is not running, or using Ollama if running.
2. **Thread Safety**: All services and bean configurations must be stateless and thread-safe.
3. **Observability**: Each call logs execution time, model provider name, and token usage via standard SLF4J logging.
4. **Virtual Threads**: Application configuration MUST set `spring.threads.virtual.enabled=true`.

---

## 6. Homework Assignment: "Multi-Model Health & Comparison Service"

### 6.1 Assignment Objective
Students must implement a resilient multi-model comparison engine that executes a prompt against available providers concurrently using Java Virtual Threads, measures latency, gathers token statistics, and handles timeout/failure gracefully if a provider is offline.

### 6.2 Student Deliverables
1. `homework/starter`:
   - A Spring Boot starter project with failing tests in `MultiModelComparisonServiceTest`.
   - `TODO` markers in `MultiModelComparisonService.java` instructing students what to implement.
2. `homework/solution`:
   - Complete reference implementation passing all tests.
   - Comprehensive test suite covering timeout, exception handling, and concurrent execution.

### 6.3 Grading Rubric (100 Points)
| Criteria | Points | Automated Test |
|---|---|---|
| Concurrent Virtual Thread Execution | 30 | `shouldExecuteConcurrentlyOnVirtualThreads` |
| Graceful Degradation on Provider Failure | 25 | `shouldHandleProviderTimeoutGracefully` |
| Accurate Token & Metric Collection | 25 | `shouldAggregateTokenAndDurationMetrics` |
| Proper Bean Routing & Configuration | 20 | `shouldRouteToSpecifiedModelProvider` |

---

## 7. Quality Gates & Acceptance Criteria
- [ ] Specification reviewed and committed (`specs/modules/module-01.spec.md`).
- [ ] Technical plan created (`specs/modules/module-01.plan.md`).
- [ ] `module-01-foundations-and-models` registered in reactor `pom.xml`.
- [ ] Bilingual lesson notes in `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`.
- [ ] Pre-compiled PDFs exported via `export-pdfs.sh`.
- [ ] All code compiles under Java 25/27 with zero warnings.
- [ ] 100% of unit and integration tests pass via `mvn test`.
