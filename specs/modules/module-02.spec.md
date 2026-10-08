# Module 02 Specification: Prompt Engineering, Prompt Templates & Context Management

**Module ID**: `module-02-prompt-engineering`  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#3](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/3)  
**Status**: APPROVED (Phase 1: Specify)  
**Authors**: Spring AI Curriculum Engineering Team  
**Last Updated**: 2026-10-08  

---

## 1. Overview & Pedagogical Objectives

### 1.1 English Narrative
Module 02 covers the science and software engineering of prompt engineering in Java. Students learn to treat prompts not as fragile string concatenations, but as versioned, parameterized, type-safe software artifacts using Spring AI's `PromptTemplate` and message hierarchies (`SystemMessage`, `UserMessage`, `AssistantMessage`).

Key pedagogical goals:
1. **Prompt Anatomy**: Master structured conversational messages (System, User, Assistant) and role separation.
2. **Spring AI `PromptTemplate`**: Use placeholder substitution (`{variable}`), resource-based templates (`classpath:prompts/...`), and caching.
3. **Few-Shot In-Context Learning**: Dynamically inject few-shot exemplars into prompts to steer model behavior without fine-tuning.
4. **Prompt Injection Defense & Sanitization**: Implement defensive system instructions and input sanitizers to guard against jailbreaks and prompt leaking.
5. **Zero-Cost Offline Execution**: Full deterministic testability using `MockChatModel` and local Ollama (`llama3.2`).

### 1.2 Türkçe Açıklama (Turkish Narrative)
Modül 02, Java ekosisteminde prompt mühendisliğinin yazılım prensiplerini ele alır. Öğrenciler prompt'ları kırılgan metin birleştirmeleri olarak değil; Spring AI'ın `PromptTemplate` ve mesaj hiyerarşisi (`SystemMessage`, `UserMessage`, `AssistantMessage`) ile sürüm kontrolüne tabi, parametreli ve tip güvenli yazılım bileşenleri olarak geliştirmeyi öğrenir.

Temel pedagojik hedefler:
1. **Prompt Anatomisi**: Rol tabanlı mesaj yapısını (System, User, Assistant) ve roller arası sınırları kavrama.
2. **Spring AI `PromptTemplate` Kullanımı**: Yer tutucu değişkenler (`{degisken}`), harici kaynak dosyalar (`classpath:prompts/...`) ve şablon önbelleğe alma.
3. **Few-Shot Örneklemeli Öğrenme**: Modeli ince ayar (fine-tuning) yapmadan birkaç kaliteli girdi-çıktı örneğiyle istenen formata yönlendirme.
4. **Prompt Enjeksiyonu Savunması**: Prompt sızdırma (leak) ve jailbreak saldırılarına karşı girdi temizleme (sanitization) ve savunmacı sistem mesajı tasarımı.
5. **Sıfır Maliyetli Test**: `MockChatModel` ve yerel Ollama ile sıfır harcama garantili geliştirme.

---

## 2. Functional Requirements

### FR-01: Resource-Based Prompt Templates
- The service MUST load prompt templates from external classpath resources (e.g. `classpath:prompts/sql-generator.st`).
- Dynamic parameter injection must support map-based and record-based context variables.

### FR-02: Few-Shot Exemplar Builder
- The service MUST provide a reusable `FewShotPromptBuilder` capable of assembling exemplars dynamically based on context or category.

### FR-03: Prompt Injection Guard
- The service MUST detect and sanitize common prompt injection vectors (e.g., `"Ignore previous instructions"`, `"System override"`).

---

## 3. Homework Assignment: "Intelligent SQL Query & Explanation Generator"

### Problem Statement
Build an enterprise SQL generation assistant that takes natural language queries and a relational database schema, and generates valid SQL queries along with step-by-step query explanations using few-shot prompt engineering.

### Grading Rubric (100 Points)
- **30 Pts**: Accurate prompt parameterization using `PromptTemplate`.
- **25 Pts**: Few-shot exemplar integration.
- **25 Pts**: Injection detection and defensive prompting.
- **20 Pts**: Unit test coverage with `MockChatModel`.
