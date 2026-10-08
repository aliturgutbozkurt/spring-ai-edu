---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #0f172a
color: #f8fafc
---

# Spring AI: Capstone Project
## Enterprise Multi-Agent Knowledge & Operations Assistant
### The Pinnacle of Enterprise Generative AI in Modern Java

---

## 1. Capstone Overview & Architecture

- The Capstone Project synthesizes all 12 modules into a unified, production-grade enterprise platform:
  - **Supervisor Agent Architecture**: Intelligent intent triaging and subagent coordination.
  - **Knowledge RAG Subagent**: Enterprise policy and contract retrieval with strict grounding.
  - **Operations Subagent**: IT service management, automated ticketing, and server diagnostics.
  - **Zero-Trust Guardrails**: Inbound PII redaction and multi-turn safety constraints.
  - **Production Observability**: Distributed tracing, latency metrics, and audit log generation.

---

## 2. Multi-Agent Orchestration Flow

- **Step 1: Input Ingestion & Redaction**:
  - Employee prompts enter via `/api/v1/assistant/chat`.
  - Guardrail filters scrub credit card and sensitive PII tokens before LLM exposure.
- **Step 2: Supervisor Decision Engine**:
  - Directs context-heavy queries to `KnowledgeRagSubagent`.
  - Routes operational commands to `OperationsToolSubagent`.
- **Step 3: Execution & Audit Aggregation**:
  - Combines subagent output into `CapstoneResponse` with complete execution audit trail.

---

## 3. High-Performance Spring Boot 3.4 Runtime

- **Project Loom Virtual Threads**:
  - High concurrency with zero blocking thread exhaustion.
- **GraalVM Native AOT Ready**:
  - Pre-registered runtime reflection hints and template bundles.
- **Actuator Health & Prometheus**:
  - Real-time AI subsystem health indicators and latency monitoring.

---

## 4. Graduation & Production Roadmap

- You have completed the Spring AI Enterprise Master Course:
  - Foundations, Prompt Engineering, Structured Outputs
  - Tool Calling, Callbacks, Advanced RAG, Multimodal AI
  - Advisors & Memory, Model Context Protocol (MCP)
  - Agentic Multi-Agent Workflows, Production Security & Native AOT
- You are now equipped to design and scale enterprise-grade AI applications in Java!
