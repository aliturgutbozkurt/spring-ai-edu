# Specification: Module 08 - Chat Advisors, Memory Systems & Conversational State

## 1. Overview
Module 08 explores conversational AI state management and Spring AI's Advisor chain. Students learn to use `ChatMemory`, `MessageChatMemoryAdvisor`, `PromptChatMemoryAdvisor`, custom advisors for token window management, and persistent conversational stores.

## 2. Learning Objectives
1. Understand conversational memory architecture in stateless LLMs.
2. Master `ChatMemory` abstraction and implementations (`InMemoryChatMemory`, Redis, JDBC).
3. Use `MessageChatMemoryAdvisor` to automatically append multi-turn context.
4. Implement a custom Token Sliding Window advisor that prunes old turns when tokens exceed a budget.
5. Build an adaptive AI Tutor maintaining student learning progress across sessions.
6. Provide offline deterministic testing for memory isolation across distinct `conversationId`s.

## 3. Architecture & Components
- **`StudentTutorSession`**: Record tracking student profile, current topic, and message history.
- **`TokenWindowChatMemoryAdvisor`**: Custom Spring AI `CallAroundAdvisor` enforcing max token window limits.
- **`TutorConversationService`**: Coordinates conversational tutoring with session memory isolation.
- **`TutorChatController`**: REST endpoint `/api/v1/tutor/chat` accepting `conversationId`, message, and returning tutor response + memory size.

## 4. Quality Gates
- Multi-turn conversation continuity tests.
- Conversation isolation tests (session A does not bleed into session B).
- Token window pruning unit tests.
- Homework starter with failing stubs; solution with 100% passing tests.
- Bilingual lesson notes and Marp compiled PDFs.
