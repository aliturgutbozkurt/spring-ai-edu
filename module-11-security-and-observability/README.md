# Module 11: Production Guardrails, Security & Observability

Welcome to **Module 11** of the Spring AI Educational Series. This module teaches you how to fortify enterprise generative AI applications with input/output guardrails, PII redaction, prompt injection defense, and OpenTelemetry observability.

## Key Features
- **PII Sanitization**: Automated regex and semantic masking of emails, credit cards, and social security numbers.
- **Prompt Injection Defense**: Detecting and mitigating jailbreak attacks before invoking LLMs.
- **Micrometer AI Metrics**: Exposing real-time telemetry (request count, latency histograms, security counter).
- **Actuator Health & Tracing**: Endpoints integrated with Spring Boot Actuator and OpenTelemetry.
- **Bilingual Documentation & Marp Slides**: Fully compiled PDF notes in English and Turkish.

## Running Tests
```bash
mvn clean test -pl module-11-security-and-observability -am
```

## Running Homework
```bash
# Starter (fails on unimplemented TODOs):
mvn test -f module-11-security-and-observability/homework/starter/pom.xml

# Solution (100% passing tests):
mvn test -f module-11-security-and-observability/homework/solution/pom.xml
```
