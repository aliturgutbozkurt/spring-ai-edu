# Module 04: Tool Calling & Function Callbacks

[English](#english) | [Türkçe](#türkçe)

---

<a name="english"></a>
## English Overview
Welcome to **Module 04** of the *Spring AI Full Course in Java*. In this module, you transform passive language models into active software agents by connecting them to external databases, microservices, and live APIs using Spring AI's `@Tool` annotations and the `ChatClient.tools(...)` API.

### Key Topics
1. **Function Calling Architecture**: Understanding JSON Schema tool generation and the tool execution loop.
2. **Spring AI `@Tool` Annotation**: Exposing Spring bean methods directly to LLMs with automatic parameter binding.
3. **Multi-Tool Orchestration**: Coordinating multiple tools (lookup, refund calculation, escalation) in a single workflow.
4. **Security Boundaries**: Enforcing permission checks and input validation on executable functions.

### Quick Start
```bash
# Run Module 04
mvn -pl module-04-tool-calling-callbacks spring-boot:run

# Test autonomous support triage
curl -X POST http://localhost:8084/api/v1/support/triage \
  -H "Content-Type: application/json" \
  -d '{"inquiry": "My order ORD-101 was damaged. I need a refund."}'
```

---

<a name="türkçe"></a>
## Türkçe Genel Bakış
*Spring AI Full Course in Java* eğitiminin **4. Modülüne** hoş geldiniz. Bu modülde, pasif dil modellerini Spring AI'ın `@Tool` anotasyonları ve `ChatClient.tools(...)` yetenekleriyle veritabanlarına ve canlı API'lere bağlayarak otonom eyleyicilere (agents) dönüştürmeyi öğreneceksiniz.

### Temel Konular
1. **Fonksiyon Çağırma Mimarisi**: JSON Schema araç tanımları ve araç yürütme döngüsü.
2. **Spring AI `@Tool` Anotasyonu**: Spring servis metotlarını LLM'e doğrudan araç olarak bağlama.
3. **Çoklu Araç Koordinasyonu**: Sipariş sorgulama, iade hesaplama ve supervisor eskalasyonu gibi araçları birlikte yürütme.
4. **Güvenlik ve Doğrulama**: Çalıştırılabilir fonksiyonlarda girdi doğrulaması ve güvenlik kontrolleri.

### Hızlı Başlangıç
```bash
# Modül 04'ü çalıştırın
mvn -pl module-04-tool-calling-callbacks spring-boot:run

# Destek talebi değerlendirme testi
curl -X POST http://localhost:8084/api/v1/support/triage \
  -H "Content-Type: application/json" \
  -d '{"inquiry": "Siparişim ORD-101 hasarlı geldi. İade talep ediyorum."}'
```
