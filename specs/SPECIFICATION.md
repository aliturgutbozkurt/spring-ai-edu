# Master Specification: Spring AI Full Course in Java (2026 September Curriculum)

**Document ID**: SPEC-2026-SPRING-AI-EDU  
**Revision**: 1.0.0 (Fall 2026 Academic & Industry Edition)  
**Methodology**: Spec-Driven Development (SDD)  
**Target Platform**: Java 27 (LTS), Spring Boot 4.0, Spring AI 1.x/2.0-M  

---

## 1. Executive Summary & Vision

Generative AI in 2026 has transitioned from experimental chatbots to mission-critical enterprise systems: autonomous agents, structured data extractors, multi-modal analyzers, and deep RAG knowledge engines. 

The goal of this course is to provide the **definitive, industry-grade, hands-on masterclass for Java developers**. Built around **Spec-Driven Development (SDD)**, every concept is specified with clear inputs, outputs, invariants, and edge cases, accompanied by runnable Java code, automated tests, bilingual courseware (Turkish & English), homework assignments with reference solutions, and an end-to-end Capstone Project.

---

## 2. Core Educational Invariants & Pedagogical Principles

1. **Zero-Barrier Local Execution ("Zero-Cost Developer Experience")**:
   - Students must be able to clone, compile, and run every single exercise locally without entering a credit card or requiring paid cloud API keys.
   - All modules support local execution via **Ollama** (`llama3.2`, `qwen2.5`, `mistral`, `nomic-embed-text`) and **Docker Compose / Testcontainers**.
   - Cloud providers (OpenAI, Google Gemini, Anthropic Claude, Azure OpenAI) are supported via switchable Spring Profiles (`-Dspring.profiles.active=openai`).

2. **Bilingual Parity (Türkçe & English)**:
   - Every module provides full documentation in English (`docs/en/lesson-notes.md`) and Turkish (`docs/tr/ders-notlari.md`).
   - All homework specifications, rubrics, and conceptual explainers exist in both languages.
   - Each markdown note is pre-configured for automated compilation into presentation-ready PDF files.

3. **Modern Java 27 & Spring Boot 4 Idioms**:
   - Heavy use of Java records for immutable data transfer, prompt models, and structured responses.
   - Virtual Threads (`Project Loom`) enabled by default for high-throughput I/O with LLMs.
   - Pattern matching, sealed interfaces, and modern stream gatherers.
   - Spring AI's modern fluent `ChatClient`, `Advisor` chains, and `ToolCallback` system.

4. **Test-First Verification (TDD & Automated Grading)**:
   - Every homework provides a `starter` project with stub implementations and failing JUnit 5 tests.
   - A verified `solution` project provides reference code and 100% passing tests.

---

## 3. Curriculum Architecture: 12 Modules & Capstone Project

### Module 01: Foundations of Spring AI, Architecture & Model Providers
- **Core Topics**: The Generative AI ecosystem in Java; Spring AI architecture (`ChatModel`, `ChatClient`, `ModelOptions`); Spring Boot auto-configuration; Model portability (OpenAI, Ollama, Anthropic, Gemini); Zero-cost local setup using Docker & Ollama.
- **Hands-on Labs**:
  - Building your first Spring AI application with fluent `ChatClient`.
  - Dynamic model switching at runtime using Spring Bean injection.
  - Streaming responses using `Flux<String>` and Server-Sent Events (SSE).
- **Homework**: "Multi-Model Health & Comparison Service" (runs prompts concurrently across multiple providers with virtual threads).

### Module 02: Prompt Engineering, Prompt Templates & Context Management
- **Core Topics**: Prompt Anatomy (System, User, Assistant messages); `PromptTemplate` and parameter substitution; Dynamic prompts with external resources; Few-shot learning in Java; Prompt versioning and prompt injection mitigation.
- **Hands-on Labs**:
  - Creating localized customer support prompt generators.
  - Few-shot code generator with template caching.
  - System prompt contextualization.
- **Homework**: "Intelligent SQL Query & Explanation Generator".

### Module 03: Structured Output, BeanOutputConverter & Type-Safe Extraction
- **Core Topics**: The non-deterministic output challenge; `BeanOutputConverter<T>`, `MapOutputConverter`, `ListOutputConverter`; JSON Schema generation under the hood; Handling malformed LLM responses with retry advisors; Java 27 records as first-class schemas.
- **Hands-on Labs**:
  - Extracting structured medical/financial reports from raw text.
  - Strongly typed sentiment and entity extraction engine.
  - Error correction advisor for JSON schema recovery.
- **Homework**: "Resume & CV Parsing Service to Java Records".

### Module 04: Tool Calling & Function Callbacks
- **Core Topics**: Function Calling mechanism; `ToolCallback` and `@Tool` annotations; Exposing Spring beans and services as LLM tools; Passing contextual metadata; Multi-step tool calls; Error handling and security boundaries for executable functions.
- **Hands-on Labs**:
  - Weather, Currency, and Live Database query tools.
  - LLM-controlled REST API invoker with input validation.
  - Tool execution logging and execution timing.
- **Homework**: "Autonomous Customer Support Ticket Triage & Resolution Agent".

### Module 05: RAG Part 1: Embeddings, Vector Databases & Document ETL
- **Core Topics**: RAG (Retrieval-Augmented Generation) lifecycle; `EmbeddingModel` (`nomic-embed-text`, `text-embedding-3-small`); Document readers (`JsonReader`, `TextReader`, `PagePdfDocumentReader`); Token chunking & `TokenTextSplitter`; Vector Stores (`PgVectorStore`, `ChromaVectorStore`, `SimpleVectorStore`).
- **Hands-on Labs**:
  - Ingestion pipeline for technical PDF manuals.
  - Vector search with similarity threshold and top-K filtering.
  - Basic Question-Answering using `QuestionAnswerAdvisor`.
- **Homework**: "Enterprise Policy Manual RAG QA Bot".

### Module 06: RAG Part 2: Advanced Retrieval, Reranking & Evaluation Metrics
- **Core Topics**: Limitations of naive RAG; Metadata filtering with vector queries; Hybrid search (dense embeddings + sparse keyword search); Contextual compression & reranking; RAG Triad evaluation: Faithfulness, Answer Relevance, Context Recall.
- **Hands-on Labs**:
  - Multi-tenant document retrieval with tenant-level metadata filtering.
  - Semantic caching to eliminate redundant LLM calls and reduce latency.
  - Automated evaluation harness using synthetic test questions.
- **Homework**: "High-Accuracy Legal Contract RAG with Automated Evaluation Suite".

### Module 07: Multimodal AI: Vision, Audio, Speech & Document OCR
- **Core Topics**: Multimodal models (`gpt-4o`, `llava`, `gemini-1.5`); Image analysis with `Media` and `MimeType`; Text-to-Speech (TTS) using `OpenAiAudioSpeechModel`; Speech-to-Text (STT/Transcription) using Whisper; Visual question answering.
- **Hands-on Labs**:
  - Receipt and invoice scanning with structured JSON extraction.
  - Voice-driven customer interaction bot (Speech-to-Text -> Chat -> Text-to-Speech).
  - Chart & diagram explainer.
- **Homework**: "Multimodal Inventory Auditing System (Image + Audio Voice Notes -> Database)".

### Module 08: Chat Advisors, Memory Systems & Conversational State
- **Core Topics**: Chat history management; `ChatMemory` abstraction; In-memory, JDBC, and Redis memory stores; `MessageChatMemoryAdvisor` and `PromptChatMemoryAdvisor`; Conversation windowing and token pruning; Conversation summarization advisors.
- **Hands-on Labs**:
  - Multi-turn conversational bot with conversation ID partitioning.
  - Long-term memory store with PostgreSQL/Redis.
  - Rolling summary advisor for token conservation.
- **Homework**: "Personal AI Tutor with Long-Term Student Progress Memory".

### Module 09: Model Context Protocol (MCP): Building Clients & Enterprise Servers
- **Core Topics**: Why MCP? The open standard for connecting AI models to data sources and tools; Spring AI MCP architecture; Building an MCP Server in Spring Boot; Consuming external MCP servers (filesystem, GitHub, databases) via `McpClient`.
- **Hands-on Labs**:
  - Developing a Spring Boot MCP Server exposing internal microservice data.
  - Configuring Spring AI `ChatClient` with MCP tool provider.
  - Combining multiple remote MCP tools seamlessly.
- **Homework**: "Enterprise Microservice Gateway with MCP Protocol Integration".

### Module 10: Agentic AI & Autonomous Multi-Agent Workflows
- **Core Topics**: ReAct (Reason + Act) loop pattern; Plan-and-Solve agents; Human-in-the-loop (approval gates); Supervisor agent coordinating specialized sub-agents (e.g., Researcher Agent, Coder Agent, Reviewer Agent); Cyclic vs DAG workflows in Java.
- **Hands-on Labs**:
  - Building a ReAct loop with Spring AI and state management.
  - Supervisor-worker multi-agent system in Spring Boot.
  - Human approval workflow using Spring Event listeners.
- **Homework**: "Autonomous Market Research & Report Writing Multi-Agent Squad".

### Module 11: Production Guardrails, Safety, Auditing & Observability
- **Core Topics**: Security risks (OWASP Top 10 for LLMs); Prompt injection detection; Content moderation using Spring AI moderation models; Token rate limiting and budget controls; Observability with Micrometer, OpenTelemetry, and Jaeger (tracing prompts, latency, token counts).
- **Hands-on Labs**:
  - Input/Output guardrail advisor intercepting toxic or unauthorized prompts.
  - Distributed tracing of multi-step RAG pipelines in Jaeger.
  - Token consumption metrics dashboard with Prometheus/Grafana.
- **Homework**: "Secure Banking AI Assistant with OWASP LLM Guardrails & Audit Log".

### Module 12: Production Deployment, GraalVM Native AOT & Cloud
- **Core Topics**: GraalVM Native Image compilation with Spring Boot 4 AOT; Instant startup (<50ms) and minimal memory footprint; Containerization with Cloud Native Buildpacks / Docker multi-stage builds; Deployment strategies on Kubernetes; Environment secret management.
- **Hands-on Labs**:
  - Compiling a Spring AI application to a GraalVM native binary.
  - Dockerizing with minimal distroless image.
  - Kubernetes manifests with readiness/liveness probes and HPA.
- **Homework**: "Sub-100ms Serverless AI Microservice Container Deployment".

### Final Capstone: Enterprise Intelligent Knowledge & Operations Platform
- **Vision**: An end-to-end, enterprise-ready intelligent assistant combining every topic from the course into a production architecture.
- **Capabilities**:
  - Ingests internal company documents (PDF, Markdown, HTML) into PgVector.
  - Multi-turn conversation with user authentication and isolated chat memory.
  - Autonomous tool execution via MCP and Spring AI Tool Callbacks.
  - ReAct multi-agent coordination for complex tasks.
  - OWASP Guardrails, PII redaction, token budgeting, and OpenTelemetry tracing.
  - Responsive Web UI (Chat, Document Upload, Tool Activity Stream) and REST/SSE endpoints.

---

## 4. Delivery & Artifact Specifications

Every module artifact folder adheres to the following specification:

```
docs/
├── en/
│   ├── lesson-notes.md    # Comprehensive theoretical & practical guide in English
│   └── lesson-notes.pdf   # Formatted PDF document
└── tr/
    ├── ders-notlari.md    # Kapsamlı teorik ve pratik Türkçe ders notları
    └── ders-notlari.pdf   # Biçimlendirilmiş PDF dokümanı
```

### PDF Compilation Specification
- Markdown notes include YAML frontmatter compatible with Marp:
  ```yaml
  ---
  marp: true
  theme: default
  paginate: true
  header: 'Spring AI Full Course (2026 Edition)'
  footer: 'Spring AI in Java - https://github.com/aliturgutbozkurt/spring-ai-edu'
  ---
  ```
- PDF generation is automated via `./scripts/export-pdfs.sh`.

---

## 5. Non-Functional Requirements & Test Matrix

- **Java Version Compatibility**: Java 25.0+ and Java 27.
- **Build Time**: Full reactor compile `< 45 seconds`.
- **Test Execution**: Offline mock test suite `< 30 seconds`.
- **Test Coverage**: Minimum 85% branch coverage on domain and advisor logic.
- **Zero Secrets in Git**: Enforced via `.gitignore` and template-based `application.yml` files.
