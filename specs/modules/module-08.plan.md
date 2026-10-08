# Implementation Plan: Module 08 - Chat Advisors, Memory Systems & Conversational State

## 1. Directory Structure
```
module-08-chat-advisors-memory/
├── pom.xml
├── src/main/java/com/springai/edu/module08/
│   ├── Module08Application.java
│   ├── config/ChatMemoryConfig.java
│   ├── model/TutorChatRequest.java
│   ├── model/TutorChatResponse.java
│   ├── advisor/TokenWindowAdvisor.java
│   ├── service/TutorConversationService.java
│   └── controller/TutorChatController.java
├── src/test/java/com/springai/edu/module08/
│   ├── TutorConversationServiceTest.java
│   └── TokenWindowAdvisorTest.java
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
2. Configure `InMemoryChatMemory` and Spring AI ChatClient advisors.
3. Implement `TokenWindowAdvisor` for bounded conversational history.
4. Implement `TutorConversationService` managing student queries.
5. Create homework starter/solution projects with unit tests.
6. Generate bilingual documentation and PDFs.
