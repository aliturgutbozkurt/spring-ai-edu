# Specification: Module 11 - Production Guardrails, Security & Observability

## 1. Overview
Module 11 addresses enterprise reliability, security, and observability in Spring AI applications. Students implement input/output guardrails (PII redaction, prompt injection filtering), Spring Boot Actuator AI metrics, OpenTelemetry distributed tracing with Micrometer, and semantic rate limiting.

## 2. Learning Objectives
1. Implement PII sanitization (redacting credit cards, SSNs, emails) using regex and LLM detectors.
2. Build input guardrails detecting jailbreak and injection attacks.
3. Configure Micrometer and OpenTelemetry for Spring AI tracing (latency, token usage, cost tracking).
4. Implement semantic rate limiting preventing DoS attacks and runaway API billing.
5. Provide offline deterministic tests for guardrails and telemetry exporters.

## 3. Architecture & Components
- **`PiiSanitizer`**: Identifies and masks sensitive customer identifiers.
- **`PromptGuardrailAdvisor`**: Spring AI `CallAroundAdvisor` intercepting prompts to block malicious injection attempts.
- **`AiMetricsRecorder`**: Records token consumption, model latencies, and error rates using Micrometer.
- **`GuardrailSecurityController`**: Exposes secure enterprise chat endpoints protected by the guardrails.

## 4. Quality Gates
- PII redaction unit tests.
- Prompt injection blocking tests.
- Micrometer AI metrics counter verification tests.
- Homework starter with failing stubs; solution with 100% passing tests.
- Bilingual lesson notes and Marp compiled PDFs.
