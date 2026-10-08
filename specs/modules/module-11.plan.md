# Implementation Plan: Module 11 - Production Guardrails, Security & Observability

## 1. Directory Structure
```
module-11-security-and-observability/
├── pom.xml
├── src/main/java/com/springai/edu/module11/
│   ├── Module11Application.java
│   ├── config/SecurityObservabilityConfig.java
│   ├── model/SecurePromptRequest.java
│   ├── model/SecurePromptResponse.java
│   ├── guardrail/PiiSanitizationService.java
│   ├── guardrail/PromptGuardrailAdvisor.java
│   ├── telemetry/AiMetricsRecorder.java
│   └── controller/GuardrailSecurityController.java
├── src/test/java/com/springai/edu/module11/
│   ├── PiiSanitizationServiceTest.java
│   └── PromptGuardrailAdvisorTest.java
├── homework/
│   ├── starter/
│   └── solution/
├── docs/
│   ├── en/lesson-notes.md
│   └── tr/ders-notlari.md
├── README.md
└── README_TR.md
```

## 2. Step-by-Step Execution
1. Create `pom.xml`.
2. Implement `PiiSanitizationService` masking sensitive financial/personal data.
3. Implement `PromptGuardrailAdvisor` enforcing safety rules.
4. Implement `AiMetricsRecorder` updating Micrometer meters.
5. Create homework starter/solution projects with unit tests.
6. Generate bilingual documentation and PDFs.
