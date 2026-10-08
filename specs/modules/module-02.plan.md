# Module 02 Technical Plan: Prompt Engineering, Prompt Templates & Context Management

**Module ID**: `module-02-prompt-engineering`  
**Specification Ref**: [module-02.spec.md](file:///Users/aliturgutbozkurt/Desktop/spring-ai-edu/specs/modules/module-02.spec.md)  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#3](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/3)  
**Status**: APPROVED (Phase 2: Plan)  
**Last Updated**: 2026-10-08  

---

## 1. Directory Structure

```
module-02-prompt-engineering/
├── pom.xml
├── README.md
├── docs/
│   ├── en/
│   │   ├── lesson-notes.md
│   │   └── lesson-notes.pdf
│   └── tr/
│       ├── ders-notlari.md
│       └── ders-notlari.pdf
├── src/
│   ├── main/
│   │   ├── java/com/springai/edu/module02/
│   │   │   ├── Module02Application.java
│   │   │   ├── config/
│   │   │   │   └── PromptConfig.java
│   │   │   ├── controller/
│   │   │   │   └── PromptController.java
│   │   │   ├── dto/
│   │   │   │   ├── SqlGenerationRequest.java
│   │   │   │   └── SqlGenerationResponse.java
│   │   │   └── service/
│   │   │       ├── FewShotPromptService.java
│   │   │       ├── PromptInjectionSanitizer.java
│   │   │       └── SqlGeneratorService.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── prompts/
│   │           ├── sql-generator-system.st
│   │           └── customer-support.st
│   └── test/
│       └── java/com/springai/edu/module02/
│           ├── PromptTemplateTest.java
│           └── SqlGeneratorServiceTest.java
└── homework/
    ├── README.md
    ├── README_TR.md
    ├── starter/
    │   ├── pom.xml
    │   └── src/
    │       ├── main/java/.../SqlGeneratorService.java (contains TODOs)
    │       └── test/java/.../SqlGeneratorServiceTest.java (fails until implemented)
    └── solution/
        ├── pom.xml
        └── src/
            ├── main/java/.../SqlGeneratorService.java (reference implementation)
            └── test/java/.../SqlGeneratorServiceTest.java (100% passes)
```

---

## 2. Core Components & Logic

1. **`PromptInjectionSanitizer`**:
   - Detects adversarial patterns (`"ignore previous"`, `"system prompt"`, `"disregard"`).
   - Strips or flags dangerous inputs before LLM consumption.
2. **`FewShotPromptService`**:
   - Assembles few-shot examples dynamically for NL-to-SQL tasks.
3. **`SqlGeneratorService`**:
   - Merges schema context, user question, and few-shot exemplars using Spring AI's `PromptTemplate`.
4. **Homework & Automated Tests**:
   - Starter project with failing stub tests.
   - Solution project passing 100% tests with `MockChatModel`.
