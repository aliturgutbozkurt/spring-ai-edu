# Module 03 Specification: Structured Output, BeanOutputConverter & Type-Safe Extraction

**Module ID**: `module-03-structured-output`  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#4](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/4)  
**Status**: APPROVED (Phase 1: Specify)  
**Authors**: Spring AI Curriculum Engineering Team  
**Last Updated**: 2026-10-08  

---

## 1. Overview & Pedagogical Objectives

### 1.1 English Narrative
LLMs natively return unstructured text. In enterprise software, downstream systems (databases, message queues, APIs) require strongly-typed data structures. Module 03 teaches students how to convert probabilistic LLM responses into deterministic Java 25/27 `record` types using Spring AI's `BeanOutputConverter<T>`, `MapOutputConverter`, and `ListOutputConverter`, along with retry advisors for schema recovery.

Key pedagogical goals:
1. **The Non-Deterministic Output Problem**: Understand hallucinations, markdown backtick pollution, and JSON parsing hazards.
2. **Spring AI `StructuredOutputConverter`**: Master `BeanOutputConverter<T>` and underlying JSON Schema generation.
3. **Fluent `ChatClient.entity(Class<T>)`**: Utilize Spring AI's high-level `.entity(...)` API for direct typed extraction.
4. **Retry & Recovery Advisors**: Intercept malformed JSON and automatically prompt the model for syntax repair.
5. **Zero-Cost Offline Execution**: Validated with `MockChatModel` and local Ollama (`llama3.2`).

### 1.2 Türkçe Açıklama (Turkish Narrative)
Büyük Dil Modelleri doğası gereği serbest metin (unstructured text) üretir. Oysa kurumsal yazılımlarda veritabanları, mesaj kuyrukları ve harici API'ler kesin tipli (strongly-typed) veri yapılarına ihtiyaç duyar. Modül 03, olasılıksal model çıktılarını Spring AI'ın `BeanOutputConverter<T>`, `MapOutputConverter` ve `ListOutputConverter` bileşenleriyle Java 25/27 `record` nesnelerine deterministik şekilde dönüştürmeyi öğretir.

Temel pedagojik hedefler:
1. **Deterministik Olmayan Çıktı Sorunu**: Markdown tırnak kirliliği, eksik alanlar ve JSON parse hatalarını anlama.
2. **Spring AI `StructuredOutputConverter`**: `BeanOutputConverter<T>` ve otomatik JSON Schema üretim mekanizması.
3. **Akıcı `.entity(Class<T>)` Kullanımı**: Spring AI'ın tip güvenli doğrudan nesne çıkarma yeteneği.
4. **Hata Düzeltme ve Yeniden Deneme (Retry Advisor)**: Bozuk JSON geldiğinde modeli uyararak şema onarımı sağlama.
5. **Sıfır Maliyetli Test**: `MockChatModel` ve yerel Ollama ile sıfır harcama garantili geliştirme.

---

## 2. Target Domain Models (Java Records)

```java
public record CandidateSkill(
    String name,
    String proficiencyLevel, // JUNIOR, MID, SENIOR, EXPERT
    int yearsOfExperience
) {}

public record CandidateExperience(
    String company,
    String role,
    int durationYears,
    List<String> highlights
) {}

public record CandidateProfile(
    String fullName,
    String email,
    String summary,
    List<CandidateSkill> skills,
    List<CandidateExperience> experiences
) {}
```

---

## 3. Homework Assignment: "Resume & CV Parsing Service to Java Records"

### Problem Statement
Build an enterprise resume parsing microservice that accepts unstructured text (PDF resume dumps or raw text), extracts structured candidate data into a `CandidateProfile` record, and validates field constraints.
