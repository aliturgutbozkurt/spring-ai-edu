# Module 03: Structured Output, BeanOutputConverter & Type-Safe Extraction

[English](#english) | [Türkçe](#türkçe)

---

<a name="english"></a>
## English Overview
Welcome to **Module 03** of the *Spring AI Full Course in Java*. In this module, you learn how to eliminate the non-deterministic output problem of LLMs by converting unstructured responses into immutable Java 25/27 `record` types using Spring AI's `BeanOutputConverter<T>`, `MapOutputConverter`, and `ListOutputConverter`.

### Key Topics
1. **The Non-Deterministic Output Challenge**: Markdown backtick pollution, schema drift, and type mismatches.
2. **Spring AI `BeanOutputConverter<T>`**: Automatic JSON Schema generation using Jackson annotations (`@JsonPropertyDescription`).
3. **Fluent `ChatClient.entity(Class<T>)`**: Direct type-safe mapping without manual deserialization.
4. **Resilience & Schema Recovery**: Handling and repairing malformed JSON responses.

### Quick Start
```bash
# Run Module 03
mvn -pl module-03-structured-output spring-boot:run

# Test resume parsing endpoint
curl -X POST http://localhost:8083/api/v1/resumes/parse \
  -H "Content-Type: text/plain" \
  -d "Sarah Connor, email: sarah.connor@cyberdyne.org, 8 years Java experience"
```

---

<a name="türkçe"></a>
## Türkçe Genel Bakış
*Spring AI Full Course in Java* eğitiminin **3. Modülüne** hoş geldiniz. Bu modülde, Büyük Dil Modellerinin deterministik olmayan serbest metin çıktılarını Spring AI'ın `BeanOutputConverter<T>`, `MapOutputConverter` ve `ListOutputConverter` araçlarıyla tip güvenli Java `record` sınıflarına dönüştürmeyi öğreneceksiniz.

### Temel Konular
1. **Deterministik Olmayan Çıktı Sorunu**: Markdown işaretleri, eksik alanlar ve JSON parse hataları.
2. **`BeanOutputConverter<T>` Kullanımı**: Jackson etiketleri (`@JsonPropertyDescription`) ile otomatik JSON Schema üretimi.
3. **Akıcı `ChatClient.entity(...)` Metodu**: Manuel dönüşüm gerekmeden doğrudan nesne üretimi.
4. **Dayanıklılık ve Şema Onarımı**: Hatalı JSON formatlarının yönetimi ve düzeltilmesi.

### Hızlı Başlangıç
```bash
# Modül 03'ü çalıştırın
mvn -pl module-03-structured-output spring-boot:run

# CV ayrıştırma testi
curl -X POST http://localhost:8083/api/v1/resumes/parse \
  -H "Content-Type: text/plain" \
  -d "Sarah Connor, email: sarah.connor@cyberdyne.org, 8 years Java experience"
```
