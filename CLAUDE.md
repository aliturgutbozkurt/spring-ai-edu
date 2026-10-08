# CLAUDE.md - Spring AI Full Course (Spec-Driven Development Guide)

> **Course**: Spring AI: Full Course in Java (Curriculum Target: Fall 2026)  
> **Tech Stack**: Java 27 (LTS), Spring Boot 4.x, Spring AI (Latest/Milestone), Maven 3.9+, Testcontainers, Ollama, Docker Compose.  
> **Methodology**: Spec-Driven Development (SDD) following Addy Osmani's AI-Assisted Engineering Patterns.

---

## 1. Spec-Driven Development (SDD) Core Workflow

Every feature, module, lesson, and assignment MUST strictly adhere to the **3-Phase SDD Lifecycle**:

```
┌─────────────────┐       ┌─────────────────┐       ┌─────────────────┐       ┌─────────────────┐
│  1. SPECIFY     │ ────> │  2. PLAN        │ ────> │  3. TASKIFY     │ ────> │  4. IMPLEMENT   │
│  (specs/spec.md)│       │  (specs/plan.md)│       │  (specs/todo.md)│       │  & TEST (TDD)   │
└─────────────────┘       └─────────────────┘       └─────────────────┘       └─────────────────┘
```

1. **Specification Phase (`specs/SPECIFICATION.md` & `specs/modules/*.spec.md`)**:
   - Define **WHAT** and **WHY** before any line of code.
   - Include user/student personas, learning outcomes, input/output schemas, error boundaries, non-functional requirements, and pedagogical objectives.
   - For educational code: specify both English and Turkish requirement narratives.

2. **Planning Phase (`specs/PLAN.md` & `specs/modules/*.plan.md`)**:
   - Architectural decisions, component hierarchy, dependency graphs, data models, package layout.
   - Dual-profile configuration (zero-cost offline Ollama/Testcontainers vs. cloud API keys).
   - Validation & acceptance criteria.

3. **Taskification Phase (`specs/TODO.md`)**:
   - Break down plans into granular, atomic, verifiable steps (15-30 min chunks).
   - Use checklist syntax (`- [ ]`, `- [x]`).
   - Associate each task with a test or automated validation step.

4. **Execution & Verification**:
   - Strictly follow the task list.
   - Write tests first (TDD) or alongside code.
   - Verify both TR & EN documentation and PDF readiness.
   - Never deviate from the spec without updating the specification artifact first.

---

## 2. Technology Stack & Baseline Standards

| Component | Standard / Version | Notes |
|---|---|---|
| **Java** | 27 (LTS) | Modern Java features: Virtual Threads, Pattern Matching, Records, Scoped Values, Stream Gatherers. |
| **Framework** | Spring Boot 4.x | Native AOT support, Observability, modern auto-configurations. |
| **AI Library** | Spring AI (Latest / 1.1+) | Fluent `ChatClient`, `ChatModel`, `VectorStore`, `Advisors`, `ToolCallback`, `McpClient`. |
| **Local LLM** | Ollama & Testcontainers | Zero-cost local execution (`llama3.3`, `qwen2.5`, `mistral`, `nomic-embed-text`). |
| **Vector DBs** | PgVector, Chroma, SimpleVectorStore | Runnable via Docker Compose and Testcontainers. |
| **Build Tool** | Apache Maven 3.9+ | Multi-module reactor build (`pom.xml`). |
| **Languages** | English & Türkçe (Dual) | All module notes, READMEs, homeworks, and solution guides MUST be bilingual. |
| **Format** | Markdown (`.md`) + PDF | Clean GitHub-flavored markdown with automated PDF conversion scripts. |

---

## 3. Architecture & Repository Layout

```
spring-ai-edu/
├── CLAUDE.md                               # This engineering guide
├── AGENTS.md                               # Symlink/Pointer to CLAUDE.md
├── pom.xml                                 # Root Aggregator & Dependency Management
├── docker-compose.yml                      # Ollama, PgVector, Chroma, Jaeger, Local Stack
├── scripts/                                # Utility scripts (PDF export, setup, test runs)
│   ├── export-pdfs.sh                      # Generates PDFs from MD using Marp/Pandoc/md-to-pdf
│   └── setup-local-ollama.sh               # Pulls required models locally
├── specs/                                  # Master SDD Artifacts
│   ├── SPECIFICATION.md                    # Global Curriculum Specification
│   ├── PLAN.md                             # Global Implementation Plan
│   ├── TODO.md                             # Master Task & Issue Tracker
│   └── modules/                            # Per-module granular specifications
├── shared-common/                          # Shared test utilities, configs, mock providers
├── module-01-foundations-and-models/       # Module 1: Core concepts, ChatClient, Models
├── module-02-prompt-engineering/           # Module 2: Prompt templates, system prompts
├── module-03-structured-output/            # Module 3: BeanOutputConverter, JSON Schema
├── module-04-tool-calling-callbacks/       # Module 4: Function calling, Tool callbacks
├── module-05-rag-and-vector-databases/     # Module 5: Embeddings, Document ETL, PgVector
├── module-06-advanced-rag-evaluation/      # Module 6: Re-ranking, Contextual RAG, Evaluation
├── module-07-multimodal-vision-audio/      # Module 7: Image analysis, TTS/STT, Multimodal
├── module-08-advisors-memory-history/      # Module 8: Chat memory, MessageChatMemoryAdvisor
├── module-09-mcp-model-context-protocol/   # Module 9: MCP Client & Server in Spring AI
├── module-10-agentic-workflows/            # Module 10: Multi-agent coordination, loops
├── module-11-security-and-observability/   # Module 11: Guardrails, Micrometer, tracing
├── module-12-production-native-aot/        # Module 12: GraalVM native images, Docker, K8s
└── capstone-enterprise-ai-assistant/       # Final Project: Full-stack Intelligent Copilot
```

### Module Structure Blueprint (Mandatory for Every Module)
Each `module-XX-*` folder contains:
```
module-XX-name/
├── pom.xml
├── README.md                               # Quick start in EN & TR
├── docs/
│   ├── en/
│   │   ├── lesson-notes.md                 # English lesson notes (Marp-ready for PDF)
│   │   └── lesson-notes.pdf                # Generated/Pre-compiled PDF
│   └── tr/
│       ├── ders-notlari.md                 # Turkish lesson notes (Marp-ready for PDF)
│       └── ders-notlari.pdf                # Generated/Pre-compiled PDF
├── src/
│   ├── main/java/...                       # Runnable student examples
│   ├── main/resources/
│   │   ├── application.yml                 # Default config (Ollama local profile)
│   │   └── application-openai.yml          # Optional cloud profile
│   └── test/java/...                       # Unit & integration tests
└── homework/
    ├── README.md                           # Problem statement & rubric (EN)
    ├── README_TR.md                        # Problem statement & rubric (TR)
    ├── starter/                            # Starter code with TODOs for students
    └── solution/                           # Reference solution with automated tests
```

---

## 4. Coding & Design Standards

### 4.1 Modern Java 27 Standards
- **Records**: Use immutable `record` types for DTOs, prompt parameters, and structured outputs.
- **Pattern Matching**: Utilize modern pattern matching for `switch` and `instanceof`.
- **Virtual Threads**: Enable `spring.threads.virtual.enabled=true` across all services.
- **Sealed Types & Enums**: Model domain states with sealed interfaces and rich enums.
- **No Lombok boilerplate where modern Java suffices**: Prefer standard records and compact constructors unless Lombok builder pattern is strictly cleaner for complex multi-field configurations.

### 4.2 Spring AI Standards
- **Fluent ChatClient**: Always prefer `ChatClient.builder(chatModel).build()` over deprecated raw model calls unless demonstrating low-level details.
- **Advisors Pipeline**: Use Advisors (`PromptChatMemoryAdvisor`, `QuestionAnswerAdvisor`, `SimpleLoggerAdvisor`) for modular cross-cutting concerns.
- **Structured Output**: Use typed records with `@JsonProperty` and `BeanOutputConverter<T>` for deterministic outputs.
- **Zero-Cost First (Developer Ergonomics)**:
  - Default profile `spring.profiles.active=local` MUST connect to local Ollama or Testcontainers.
  - Students must NOT be forced to enter an OpenAI/Anthropic API key to run and pass course exercises.
  - Cloud profiles (`openai`, `gemini`, `anthropic`) are provided as optional alternative configurations.

### 4.3 Documentation & PDF Export Standards
- Markdown files must use clean GitHub-flavored markdown with Marp frontmatter or Pandoc compatibility:
  ```markdown
  ---
  marp: true
  theme: gaia
  paginate: true
  ---
  ```
- Use code blocks with syntax highlighting (`java`, `json`, `yaml`, `bash`).
- Include Mermaid diagrams for architectural workflows.
- Bilingual parity: Every concept explained in English must have an equally detailed explanation in Turkish.

---

## 5. Development & Test Commands

### Build & Run
```bash
# Compile entire course reactor
mvn clean compile

# Run tests with offline/mock profile (fast)
mvn test -Dspring.profiles.active=test

# Run a specific module
mvn -pl module-01-foundations-and-models spring-boot:run

# Run with local Ollama profile
mvn -pl module-01-foundations-and-models spring-boot:run -Dspring-boot.run.profiles=local

# Run with OpenAI profile (requires OPENAI_API_KEY environment variable)
OPENAI_API_KEY="sk-..." mvn -pl module-01-foundations-and-models spring-boot:run -Dspring-boot.run.profiles=openai
```

### Docker Services
```bash
# Start local Ollama, PgVector, and Chroma
docker compose up -d

# Pull required local models
docker compose exec ollama ollama pull llama3.2
docker compose exec ollama ollama pull nomic-embed-text
```

### Documentation & PDF Generation
```bash
# Generate all PDFs from markdown notes
./scripts/export-pdfs.sh
```

---

## 6. GitHub & SDD Issue Management Conventions

- Every issue corresponds to an SDD artifact or milestone.
- **Labels**:
  - `sdd:spec`: Specification definition
  - `sdd:plan`: Technical plan
  - `sdd:task`: Implementation task
  - `course:module`: Educational module
  - `course:capstone`: Capstone project
  - `docs:bilingual`: TR/EN Documentation
- Commit messages follow conventional commits:
  - `feat(module-01): implement ChatClient fluent API examples`
  - `docs(module-01): add TR and EN lesson notes with PDF support`
  - `spec(module-02): define prompt engineering requirements and rubric`
  - `test(homework-01): add automated grading test suite`

---

## 7. Quality Gates for Every Module

Before marking any module complete in `specs/TODO.md`:
1. [ ] Spec exists in `specs/modules/module-XX.spec.md`.
2. [ ] Lesson notes exist in both `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`.
3. [ ] PDF export succeeds and is formatted cleanly.
4. [ ] All Java code compiles under Java 25/27 with zero warnings.
5. [ ] Module runs with zero configuration on `local` profile (Ollama/Testcontainers).
6. [ ] Homework has clear problem description in TR & EN.
7. [ ] Starter project builds and has failing/stub tests.
8. [ ] Solution project builds and all tests pass (`mvn test`).
