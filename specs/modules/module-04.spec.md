# Module 04 Specification: Tool Calling & Function Callbacks

**Module ID**: `module-04-tool-calling-callbacks`  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#5](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/5)  
**Status**: APPROVED (Phase 1: Specify)  
**Authors**: Spring AI Curriculum Engineering Team  
**Last Updated**: 2026-10-08  

---

## 1. Overview & Pedagogical Objectives

### 1.1 English Narrative
LLMs are isolated prediction engines—they cannot natively read databases, query live APIs, or trigger side effects. Module 04 teaches students how to turn LLMs into active agents using Spring AI's modern Tool Calling (`@Tool` annotations and `FunctionCallback` / `ToolCallback`). Students learn how Spring AI converts Java method signatures and Java 25/27 records into JSON Schema tool definitions, orchestrates function invocation loops, and enforces safety boundaries.

Key pedagogical goals:
1. **The Function Calling Protocol**: How LLMs decide *when* and *with what arguments* to invoke code.
2. **Spring AI `@Tool` Annotation**: Exposing Spring service methods directly to `ChatClient`.
3. **Multi-Tool Orchestration**: Supplying multiple tools (e.g., Currency Converter + Order DB lookup) in a single conversational turn.
4. **Execution Security & Validation**: Input sanitization, authorization gates, and preventing unauthorized destructive operations.
5. **Zero-Cost Offline Execution**: Deterministic testing with `MockChatModel` and local Ollama (`llama3.2`).

### 1.2 Türkçe Açıklama (Turkish Narrative)
Büyük Dil Modelleri izole tahmin motorlarıdır; kendi başlarına canlı veritabanlarını okuyamaz, REST API çağıramaz veya sistemler üzerinde işlem yapamaz. Modül 04, modelleri Spring AI'ın modern Araç Çağırma (Tool Calling - `@Tool` anotasyonları ve `ToolCallback`) mekanizmasıyla aktif eyleyicilere (agents) dönüştürmeyi öğretir. Java metot imzalarının ve `record` tiplerinin nasıl otomatik JSON Schema araç tanımlarına dönüştüğünü, model ile uygulama arasındaki fonksiyon çağrı döngüsünü ve güvenlik sınırlarını kapsar.

Temel pedagojik hedefler:
1. **Fonksiyon Çağırma Protokolü**: Modelin hangi durumlarda ve hangi parametrelerle Java kodunu tetikleyeceğini anlaması.
2. **Spring AI `@Tool` Anotasyonu**: Spring servis metotlarının bildirimsel olarak `ChatClient`'a araç olarak bağlanması.
3. **Çoklu Araç Orkestrasyonu**: Tek bir konuşma turunda birden fazla aracı (döviz çevirici, sipariş sorgulama vb.) koordineli kullanma.
4. **Çalıştırma Güvenliği ve Doğrulama**: Parametre kontrolü ve yetkisiz tahrip edici işlemleri engelleme.
5. **Sıfır Maliyetli Test**: `MockChatModel` ve yerel Ollama ile sıfır harcama garantili geliştirme.

---

## 2. Functional Requirements

### FR-01: `@Tool` Enabled Spring Services
- A Customer Support service MUST expose methods annotated with `@Tool`:
  - `getOrderDetails(String orderId)`
  - `calculateRefund(String orderId, double amount, String reason)`
  - `escalateToHumanAgent(String ticketId, String urgency)`

### FR-02: Tool Registration in ChatClient
- The `ChatClient` MUST register tools fluently via `.tools(...)` or `.defaultTools(...)`.

### FR-03: Multi-Turn Autonomous Triage
- When given a customer support query, the model autonomously calls lookup tools, calculates refund eligibility, and returns a final response to the user.

---

## 3. Homework Assignment: "Autonomous Customer Support Ticket Triage Agent"

### Problem Statement
Build an autonomous support agent that receives customer complaints, queries order history via a mock database tool, calculates eligible compensation via a policy tool, and either issues a resolution or escalates the ticket.
