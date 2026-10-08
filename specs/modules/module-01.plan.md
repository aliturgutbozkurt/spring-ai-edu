# Module 01 Technical Plan: Foundations of Spring AI, Architecture & Model Providers

**Module ID**: `module-01-foundations-and-models`  
**Specification Ref**: [module-01.spec.md](file:///Users/aliturgutbozkurt/Desktop/spring-ai-edu/specs/modules/module-01.spec.md)  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#2](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/2)  
**Status**: APPROVED (Phase 2: Plan)  
**Last Updated**: 2026-10-08  

---

## 1. Architectural Architecture & File Tree

```
module-01-foundations-and-models/
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
│   │   ├── java/com/springai/edu/module01/
│   │   │   ├── Module01Application.java
│   │   │   ├── config/
│   │   │   │   ├── ChatClientConfig.java
│   │   │   │   └── ModelRoutingConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── ChatController.java
│   │   │   │   └── ModelHealthController.java
│   │   │   ├── dto/
│   │   │   │   ├── ChatPromptRequest.java
│   │   │   │   ├── ChatPromptResponse.java
│   │   │   │   ├── ModelHealthStatus.java
│   │   │   │   └── MultiModelComparisonResult.java
│   │   │   └── service/
│   │   │       ├── ChatService.java
│   │   │       ├── ModelRoutingService.java
│   │   │       └── MultiModelComparisonService.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── application-openai.yml
│   └── test/
│       └── java/com/springai/edu/module01/
│           ├── ChatClientConfigTest.java
│           ├── controller/ChatControllerTest.java
│           └── service/MultiModelComparisonServiceTest.java
└── homework/
    ├── README.md
    ├── README_TR.md
    ├── starter/
    │   ├── pom.xml
    │   └── src/
    │       ├── main/java/.../MultiModelComparisonService.java (contains TODOs)
    │       └── test/java/.../MultiModelComparisonServiceTest.java (fails until implemented)
    └── solution/
        ├── pom.xml
        └── src/
            ├── main/java/.../MultiModelComparisonService.java (reference implementation)
            └── test/java/.../MultiModelComparisonServiceTest.java (100% passes)
```

---

## 2. Dependency Management & POM Structure

The module inherits from `spring-ai-edu-parent`:
```xml
<dependencies>
    <!-- Shared Common & Mock Models -->
    <dependency>
        <groupId>com.springai.edu</groupId>
        <artifactId>shared-common</artifactId>
    </dependency>

    <!-- Spring Boot Web & WebFlux (for SSE Streaming) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Spring AI Ollama (Primary local provider) -->
    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-ollama-spring-boot-starter</artifactId>
    </dependency>

    <!-- Spring AI OpenAI (Optional cloud provider) -->
    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Test Dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.projectreactor</groupId>
        <artifactId>reactor-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 3. Core Component Design

### 3.1 `ChatClientConfig`
- Configures default `ChatClient` using the default primary model (`ollamaChatModel` or `mockChatModel`).
- Sets up default system instructions: `"You are an expert Spring AI assistant built with modern Java 25/27."`
- Injects a `SimpleLoggerAdvisor` to log request and response metadata.

### 3.2 `ModelRoutingConfig` & `ModelRoutingService`
- Detects available `ChatModel` beans in the Spring context.
- Provides fallback to `MockChatModel` if Ollama is unreachable, ensuring deterministic offline test runs.
- Offers `getModel(String providerName)` method to dynamically fetch the requested `ChatModel`.

### 3.3 `MultiModelComparisonService` (Concurrent Virtual Threads)
- Takes a prompt string.
- Executes the prompt concurrently against all registered active models using Java 25/27 Virtual Threads (`Executors.newVirtualThreadPerTaskExecutor()`).
- Collects duration in milliseconds, token counts, and generated text.
- If one model fails or times out, it captures the error in the response record without blowing up the entire comparison.

---

## 4. Bilingual Documentation & Marp Presentation Design
Both `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md` will contain:
1. **Slide Frontmatter**:
   ```markdown
   ---
   marp: true
   theme: gaia
   paginate: true
   header: "Spring AI Full Course - Module 01"
   footer: "© 2026 aliturgutbozkurt/spring-ai-edu"
   ---
   ```
2. **Mermaid Diagrams**:
   - Spring AI Client vs Model abstraction diagram.
   - Streaming SSE pipeline diagram.
   - Virtual Thread concurrent routing diagram.
3. **Hands-on Quickstarts & Code Snippets**:
   - `curl` commands for testing endpoints.
   - How to run with local Ollama vs offline mock profile.

---

## 5. Homework & Grading Engine Design
- **Starter Project**:
  - Contains student exercise instructions in both English and Turkish.
  - The `compareModelsConcurrently` method throws `UnsupportedOperationException("TODO: Implement concurrent model comparison with Virtual Threads")`.
  - Starter test suite fails cleanly with clear descriptive messages.
- **Solution Project**:
  - Implements the complete solution using `StructuredTaskScope` / `CompletableFuture` on Virtual Threads.
  - All JUnit 5 assertions pass.

---

## 6. Execution Steps & Verification Checklist
1. Create `module-01-foundations-and-models` directory structure and `pom.xml`.
2. Register module in root `pom.xml`.
3. Implement DTO records (`ChatPromptRequest`, `ChatPromptResponse`, etc.).
4. Implement configuration classes (`ChatClientConfig`, `ModelRoutingConfig`).
5. Implement services (`ChatService`, `ModelRoutingService`, `MultiModelComparisonService`).
6. Implement controllers (`ChatController`, `ModelHealthController`).
7. Write unit and integration tests.
8. Implement `homework/starter` and `homework/solution`.
9. Create bilingual lesson notes in `docs/en/` and `docs/tr/`.
10. Compile and verify PDFs using `scripts/export-pdfs.sh`.
11. Run full reactor build `mvn clean test`.
