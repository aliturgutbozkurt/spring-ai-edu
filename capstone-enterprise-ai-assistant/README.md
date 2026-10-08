# Capstone Project: Enterprise Multi-Agent Knowledge & Operations Assistant

Welcome to the **Capstone Project** of the Spring AI Enterprise Master Course. This project consolidates everything taught in Modules 01 through 12 into a comprehensive, production-ready enterprise assistant application.

---

## 🌟 Architecture & Capabilities

1. **Enterprise Supervisor Agent**: Central triage orchestrator that dynamically inspects incoming prompts, applies PII sanitization guardrails, and routes tasks to specialized subagents.
2. **Knowledge RAG Subagent**: Enterprise document grounding covering corporate HR policies, SLAs, and guidelines.
3. **Operations Tool Subagent**: Action executor for IT support ticketing, server cluster diagnostics, and account management.
4. **Production Quality & Observability**: Complete audit trail generation, Project Loom virtual threads, Actuator health endpoints, and GraalVM Native AOT compatibility.

---

## 📁 Project Structure

```
capstone-enterprise-ai-assistant/
├── pom.xml
├── src/main/java/com/springai/edu/capstone/
│   ├── CapstoneApplication.java
│   ├── agent/
│   │   ├── EnterpriseSupervisorAgent.java
│   │   ├── KnowledgeRagSubagent.java
│   │   └── OperationsToolSubagent.java
│   ├── config/CapstoneConfig.java
│   ├── controller/EnterpriseAssistantController.java
│   └── model/
│       ├── CapstoneRequest.java
│       └── CapstoneResponse.java
├── src/main/resources/application.yml
├── docs/
│   ├── en/lesson-notes.md (Marp presentation)
│   └── tr/ders-notlari.md (Marp sunumu)
└── src/test/java/com/springai/edu/capstone/
    ├── EnterpriseAssistantControllerTest.java
    └── EnterpriseSupervisorAgentTest.java
```

---

## 🛠️ Build and Run

```bash
mvn clean test -pl capstone-enterprise-ai-assistant -am
```
