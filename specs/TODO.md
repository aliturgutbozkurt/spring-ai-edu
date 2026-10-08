# Master Task Tracker & Roadmap: Spring AI Full Course (2026 Edition)

**Document ID**: TODO-2026-SPRING-AI-EDU  
**Specification Ref**: [SPECIFICATION.md](file:///Users/aliturgutbozkurt/Desktop/spring-ai-edu/specs/SPECIFICATION.md)  
**Plan Ref**: [PLAN.md](file:///Users/aliturgutbozkurt/Desktop/spring-ai-edu/specs/PLAN.md)  
**Methodology**: Spec-Driven Development (SDD) Checklist  

---

## Milestone 1: Environment, Root Reactor & SDD Infrastructure
- [x] Create `CLAUDE.md` and `AGENTS.md` with best practices, SDD guidelines, and Java 27/Spring Boot 4 standards.
- [x] Create master `specs/SPECIFICATION.md`.
- [x] Create master `specs/PLAN.md`.
- [x] Create master `specs/TODO.md`.
- [x] Initialize Git repository and create public GitHub repository `aliturgutbozkurt/spring-ai-edu`.
- [x] Create Root `pom.xml` (Maven reactor with Java 27, Spring Boot 4 / 3.4 baseline, Spring AI BOM, Testcontainers).
- [x] Create `docker-compose.yml` (Ollama, PostgreSQL with pgvector, Chroma, Jaeger).
- [x] Create `scripts/setup-local-ollama.sh` (automatic model pulls: `llama3.2`, `nomic-embed-text`).
- [x] Create `scripts/export-pdfs.sh` (Node/Marp automated PDF generation toolchain).
- [x] Create `shared-common` module (Deterministic `MockChatModel`, test fixtures, shared records).
- [x] Populate GitHub Issues corresponding to each module and milestone using `gh issue create`.

---

## Milestone 2: Core Course Delivery (Modules 01 to 04)
- [x] **Module 01: Foundations of Spring AI, Architecture & Model Providers**
  - [x] Write `specs/modules/module-01.spec.md`
  - [x] Write `specs/modules/module-01.plan.md`
  - [x] Create `module-01-foundations-and-models/pom.xml` and register in root reactor
  - [x] Implement DTOs, configuration, and multi-model routing services
  - [x] Implement fluent `ChatClient` controllers and streaming SSE endpoints
  - [x] Implement Virtual Thread multi-model comparison engine
  - [x] Write comprehensive unit and integration tests (100% passing)
  - [x] Write bilingual lesson notes (`docs/en/lesson-notes.md` & `docs/tr/ders-notlari.md`)
  - [x] Implement homework assignment (`homework/starter` and `homework/solution`)
  - [x] Generate bilingual PDFs (`lesson-notes.pdf` & `ders-notlari.pdf`)
  - [x] Verify all quality gates and update GitHub Issue #2
- [x] **Module 02: Prompt Engineering, Prompt Templates & Context Management**
  - [x] Write `specs/modules/module-02.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: `PromptTemplate`, system prompts, dynamic context, few-shot
  - [x] Implement `homework/starter` & `homework/solution` (SQL query generator with rubric)
  - [x] Generate bilingual PDFs
  - [x] Verify all quality gates and update GitHub Issue #3
- [x] **Module 03: Structured Output, BeanOutputConverter & Type-Safe Extraction**
  - [x] Write `specs/modules/module-03.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: `BeanOutputConverter`, JSON Schema, Java 27 record mapping, retry advisors
  - [x] Implement `homework/starter` & `homework/solution` (Resume/CV parser to strongly-typed records)
  - [x] Generate bilingual PDFs
  - [x] Verify all quality gates and update GitHub Issue #4
- [x] **Module 04: Tool Calling & Function Callbacks**
  - [x] Write `specs/modules/module-04.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: `@Tool` annotations, `ToolCallback`, external REST integration
  - [x] Implement `homework/starter` & `homework/solution` (Autonomous Customer Support Ticket Triage)
  - [x] Generate bilingual PDFs
  - [x] Verify all quality gates and update GitHub Issue #5

---

## Milestone 3: RAG, Embeddings & Multimodal AI (Modules 05 to 08)
- [x] **Module 05: RAG Part 1: Embeddings, Vector Databases & Document ETL**
  - [x] Write `specs/modules/module-05.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: Document readers, `TokenTextSplitter`, `EmbeddingModel`, `PgVectorStore`
  - [x] Implement `homework/starter` & `homework/solution` (Enterprise Policy QA Bot)
  - [x] Generate bilingual PDFs
  - [x] Verify all quality gates and update GitHub Issue #6
- [x] **Module 06: RAG Part 2: Advanced Retrieval, Reranking & Evaluation Metrics**
  - [x] Write `specs/modules/module-06.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: Hybrid search, metadata filtering, semantic caching, RAG Triad evaluation
  - [x] Implement `homework/starter` & `homework/solution` (High-Accuracy Contract RAG)
  - [x] Generate bilingual PDFs
  - [x] Verify all quality gates and update GitHub Issue #7
- [x] **Module 07: Multimodal AI: Vision, Audio, Speech & Document OCR**
  - [x] Write `specs/modules/module-07.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: Multimodal vision (receipt OCR), Whisper STT, TTS speech synthesis
  - [x] Implement `homework/starter` & `homework/solution` (Multimodal Inventory Auditing System)
  - [x] Generate bilingual PDFs
  - [x] Verify all quality gates and update GitHub Issue #8
- [x] **Module 08: Chat Advisors, Memory Systems & Conversational State**
  - [x] Write `specs/modules/module-08.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: `MessageChatMemoryAdvisor`, JDBC/Redis memory stores, token windowing
  - [x] Implement `homework/starter` & `homework/solution` (Personal AI Tutor with long-term memory)
  - [x] Generate bilingual PDFs
  - [x] Verify all quality gates and update GitHub Issue #9

---

## Milestone 4: Advanced Systems, Agents & Production (Modules 09 to 12)
- [x] **Module 09: Model Context Protocol (MCP): Building Clients & Enterprise Servers**
  - [x] Write `specs/modules/module-09.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: Spring Boot MCP Server + Spring AI MCP Client integration
  - [x] Implement `homework/starter` & `homework/solution` (Enterprise Microservice Gateway via MCP)
  - [x] Generate bilingual PDFs
- [x] **Module 10: Agentic AI & Autonomous Multi-Agent Workflows**
  - [x] Write `specs/modules/module-10.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: ReAct loop, supervisor-subagent delegation, human-in-the-loop gates
  - [x] Implement `homework/starter` & `homework/solution` (Autonomous Market Research Agent Squad)
  - [x] Generate bilingual PDFs
- [x] **Module 11: Production Guardrails, Safety, Auditing & Observability**
  - [x] Write `specs/modules/module-11.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: OWASP guardrail advisor, prompt injection defense, Jaeger tracing
  - [x] Implement `homework/starter` & `homework/solution` (Secure Banking Assistant with Audit Log)
  - [x] Generate bilingual PDFs
- [x] **Module 12: Production Deployment, GraalVM Native AOT & Cloud**
  - [x] Write `specs/modules/module-12.spec.md`
  - [x] Write `docs/en/lesson-notes.md` and `docs/tr/ders-notlari.md`
  - [x] Implement runnable code: GraalVM AOT native compilation, Docker multi-stage, Kubernetes manifests
  - [x] Implement `homework/starter` & `homework/solution` (Sub-100ms Native AI Microservice)
  - [x] Generate bilingual PDFs

---

## Milestone 5: Capstone Project & Global Release
- [x] **Capstone: Enterprise Intelligent Knowledge & Operations Platform**
  - [x] Write `specs/modules/capstone.spec.md`
  - [x] Ingest internal company knowledge into PgVector
  - [x] Multi-turn authenticated chat with user-isolated memory
  - [x] Multi-Agent ReAct workflow coordinating MCP tools
  - [x] Production Guardrails, PII redaction, token quota manager
  - [x] Interactive Web UI + SSE streaming endpoints
  - [x] Automated end-to-end integration test suite
- [x] **Global Release & Validation**
  - [x] Complete full reactor `mvn clean test` run
  - [x] Compile and verify all bilingual PDFs across all modules
  - [x] Push all commits to GitHub repository and update issue statuses
