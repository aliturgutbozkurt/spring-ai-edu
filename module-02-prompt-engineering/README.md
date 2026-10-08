# Module 02: Prompt Engineering, Prompt Templates & Context Management

[English](#english) | [Türkçe](#türkçe)

---

<a name="english"></a>
## English Overview
Welcome to **Module 02** of the *Spring AI Full Course in Java*. This module explores the software engineering principles of prompt engineering using Spring AI, `PromptTemplate`, resource-backed prompts, few-shot in-context learning, and defensive prompt injection sanitization.

### Key Topics
1. **Prompt Anatomy**: Structured separation of `SystemMessage`, `UserMessage`, and `AssistantMessage`.
2. **Spring AI `PromptTemplate`**: Loading from classpath resources (`classpath:prompts/*.st`) and type-safe placeholder rendering.
3. **Few-Shot In-Context Learning**: Dynamically assembling exemplars to guide output formatting without fine-tuning.
4. **Prompt Injection Mitigation**: Identifying adversarial input patterns and protecting system instructions.

### Quick Start
```bash
# Run Module 02
mvn -pl module-02-prompt-engineering spring-boot:run

# Test SQL generation
curl -X POST http://localhost:8082/api/v1/prompts/sql \
  -H "Content-Type: application/json" \
  -d '{"question": "Show all active customers"}'
```

---

<a name="türkçe"></a>
## Türkçe Genel Bakış
*Spring AI Full Course in Java* eğitiminin **2. Modülüne** hoş geldiniz. Bu modülde prompt mühendisliğini bir yazılım disiplini olarak ele alacak; `PromptTemplate`, harici şablon dosyaları, few-shot örneklemeli öğrenme ve prompt enjeksiyonu savunma mekanizmalarını öğreneceksiniz.

### Temel Konular
1. **Prompt Anatomisi**: `SystemMessage`, `UserMessage` ve `AssistantMessage` rolleri.
2. **Spring AI `PromptTemplate`**: `classpath:prompts/*.st` dosyalarından dinamik parametre yükleme.
3. **Few-Shot Öğrenme**: Modeli yönlendirmek için dinamik örnek blokları oluşturma.
4. **Prompt Enjeksiyonu Savunması**: Jailbreak ve sistem promptu sızdırma girişimlerini engelleme.

### Hızlı Başlangıç
```bash
# Modül 02'yi çalıştırın
mvn -pl module-02-prompt-engineering spring-boot:run

# SQL üretim testi
curl -X POST http://localhost:8082/api/v1/prompts/sql \
  -H "Content-Type: application/json" \
  -d '{"question": "Show all active customers"}'
```
