# Module 08: Chat Advisors, Memory Systems & Conversational State

Welcome to **Module 08** of the Spring AI Educational Series. This module teaches you how to maintain conversational state and context across multi-turn interactions using Spring AI's Advisor chain and `ChatMemory` architectures.

## Key Features
- **Conversational Memory**: `ChatMemory` abstraction and `MessageChatMemoryAdvisor`.
- **Session Isolation**: Dynamic routing per `conversationId`.
- **Sliding Window Token Advisor**: Preventing context exhaustion by pruning older turns automatically.
- **Personal AI Tutor**: Context-aware educational assistant that preserves lesson flow.
- **Bilingual Documentation & Marp Slides**: Fully compiled PDF notes in English and Turkish.

## Running Tests
```bash
mvn clean test -pl module-08-advisors-memory-history -am
```

## Running Homework
```bash
# Starter (fails on unimplemented TODOs):
mvn test -f module-08-advisors-memory-history/homework/starter/pom.xml

# Solution (100% passing tests):
mvn test -f module-08-advisors-memory-history/homework/solution/pom.xml
```
