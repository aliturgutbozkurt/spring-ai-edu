# Module 04 Technical Plan: Tool Calling & Function Callbacks

**Module ID**: `module-04-tool-calling-callbacks`  
**Specification Ref**: [module-04.spec.md](file:///Users/aliturgutbozkurt/Desktop/spring-ai-edu/specs/modules/module-04.spec.md)  
**Milestone**: Milestone 2 (Core Course Delivery)  
**Tracking Issue**: [#5](https://github.com/aliturgutbozkurt/spring-ai-edu/issues/5)  
**Status**: APPROVED (Phase 2: Plan)  
**Last Updated**: 2026-10-08  

---

## 1. Directory Structure

```
module-04-tool-calling-callbacks/
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
│   │   ├── java/com/springai/edu/module04/
│   │   │   ├── Module04Application.java
│   │   │   ├── config/
│   │   │   │   └── ToolCallingConfig.java
│   │   │   ├── controller/
│   │   │   │   └── SupportTicketController.java
│   │   │   ├── model/
│   │   │   │   ├── OrderRecord.java
│   │   │   │   ├── RefundDecision.java
│   │   │   │   └── TicketTriageResult.java
│   │   │   ├── service/
│   │   │   │   ├── CustomerSupportService.java
│   │   │   │   └── SupportTools.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/springai/edu/module04/
│           ├── SupportToolsTest.java
│           └── CustomerSupportServiceTest.java
└── homework/
    ├── README.md
    ├── README_TR.md
    ├── starter/
    │   ├── pom.xml
    │   └── src/
    │       ├── main/java/.../SupportTools.java
    │       └── test/java/.../SupportToolsTest.java
    └── solution/
        ├── pom.xml
        └── src/
            ├── main/java/.../SupportTools.java
            └── test/java/.../SupportToolsTest.java
```

---

## 2. Implementation Steps
1. Create `module-04-tool-calling-callbacks/pom.xml` and register in root `pom.xml`.
2. Implement `@Tool` annotated methods in `SupportTools.java`:
   - `lookupOrder(String orderId)`
   - `calculateRefund(String orderId, double requestedAmount, String reason)`
3. Implement `CustomerSupportService` binding tools to `ChatClient`.
4. Implement `SupportTicketController` (`POST /api/v1/support/triage`).
5. Write unit tests with `MockChatModel`.
6. Implement homework starter & solution.
7. Generate bilingual lesson notes and PDFs.
