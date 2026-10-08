# Specification: Capstone Project - Enterprise Multi-Agent Knowledge & Operations Assistant

## 1. Overview
The Capstone Project synthesizes all skills acquired across Modules 01 to 12 into a production-grade, end-to-end Enterprise AI Assistant. The system features multi-agent coordination, hybrid RAG with pgvector, MCP tool integration, multimodal document audit, chat memory advisors, and strict production guardrails.

## 2. Learning Objectives
1. Architect an end-to-end multi-agent system solving complex real-world enterprise workflows.
2. Unify RAG, Tool Calling, MCP, Multimodal Vision, and Chat Memory in a cohesive Spring Boot application.
3. Enforce enterprise guardrails (PII redaction, token budgets, safety filters).
4. Provide comprehensive unit, integration, and evaluation suites (100% passing tests).
5. Compile production-ready artifacts with full bilingual documentation and Marp presentation slides.

## 3. Architecture & Components
- **`EnterpriseSupervisorAgent`**: Triages incoming employee requests to specialized subagents:
  - **`KnowledgeRagAgent`**: High-accuracy contract and policy RAG with RAG Triad evaluation.
  - **`OperationsToolAgent`**: ERP tool caller and MCP gateway executor.
  - **`AuditVisionAgent`**: Multimodal receipt and document scanner.
- **`EnterpriseAssistantController`**: REST and SSE streaming endpoints for multi-turn conversations.

## 4. Quality Gates
- End-to-end orchestration tests.
- Offline deterministic tests for all subagents.
- Bilingual lesson notes and Marp compiled PDFs.
