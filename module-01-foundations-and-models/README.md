# Module 01: Foundations of Spring AI, Architecture & Model Providers

[English](#english) | [Türkçe](#türkçe)

---

<a name="english"></a>
## English Overview

Welcome to **Module 01** of the *Spring AI Full Course in Java*. This module introduces you to the core mental models and architectural building blocks of Spring AI using **Java 25/27** and **Spring Boot 3.4/4.x**.

### Key Topics
1. **Spring AI Architecture**: Why abstractions matter (`ChatModel`, `ChatClient`, `Prompt`, `ChatResponse`).
2. **The Fluent `ChatClient` API**: Moving beyond legacy static models to declarative, chained LLM interactions.
3. **Model Portability & Dynamic Routing**: Seamlessly swapping Ollama, OpenAI, Anthropic, or deterministic Mocks at runtime without altering core application logic.
4. **Reactive Streaming via SSE**: Low-latency token delivery over Server-Sent Events with `Flux<String>`.
5. **Java 25/27 Virtual Threads (Project Loom)**: Concurrent, non-blocking model queries at extreme scale.

### Quick Start
```bash
# 1. Run with zero-cost local profile (deterministic mock fallback / local Ollama)
mvn spring-boot:run

# 2. Test synchronous chat
curl -X POST http://localhost:8081/api/v1/chat \
  -H "Content-Type: application/json" \
  -d '{"prompt": "hello"}'

# 3. Test streaming SSE
curl -N http://localhost:8081/api/v1/chat/stream?prompt=hello

# 4. Probe provider health
curl http://localhost:8081/api/v1/models/health

# 5. Run concurrent multi-model benchmark
curl -X POST http://localhost:8081/api/v1/models/compare \
  -H "Content-Type: application/json" \
  -d '{"prompt": "ping"}'
```

---

<a name="türkçe"></a>
## Türkçe Genel Bakış

*Spring AI Full Course in Java* eğitiminin **1. Modülüne** hoş geldiniz. Bu modülde, **Java 25/27** ve **Spring Boot 3.4/4.x** standartlarını kullanarak Spring AI'ın temel mimari yapı taşlarını ve zihinsel modellerini öğreneceksiniz.

### Temel Konular
1. **Spring AI Mimarisi**: Soyutlama katmanının önemi (`ChatModel`, `ChatClient`, `Prompt`, `ChatResponse`).
2. **Akıcı (Fluent) `ChatClient` API'si**: Eski model çağrılarından kurtulup bildirimsel (declarative) LLM etkileşimlerine geçiş.
3. **Model Taşınabilirliği ve Dinamik Yönlendirme**: Ollama, OpenAI ve Mock modelleri arasında kod değiştirmeden çalışma zamanında geçiş yapma.
4. **SSE ile Reaktif Akış (Streaming)**: `Flux<String>` ile Server-Sent Events üzerinden gecikmesiz token akışı.
5. **Java 25/27 Sanal İş Parçacıkları (Virtual Threads)**: Yüksek eşzamanlılıkla model çağırma ve performans testi.

### Hızlı Başlangıç
```bash
# 1. Sıfır maliyetli yerel profille çalıştırın
mvn spring-boot:run

# 2. Senkron chat testi
curl -X POST http://localhost:8081/api/v1/chat \
  -H "Content-Type: application/json" \
  -d '{"prompt": "hello"}'

# 3. Akış (streaming) testi
curl -N http://localhost:8081/api/v1/chat/stream?prompt=hello

# 4. Model sağlık kontrolü
curl http://localhost:8081/api/v1/models/health

# 5. Eşzamanlı model karşılaştırma
curl -X POST http://localhost:8081/api/v1/models/compare \
  -H "Content-Type: application/json" \
  -d '{"prompt": "ping"}'
```
