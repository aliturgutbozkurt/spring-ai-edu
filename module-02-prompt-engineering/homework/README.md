# Homework 02: Intelligent SQL Query & Explanation Generator (English)

## Objective
Build a parameterized prompt pipeline using Spring AI's `PromptTemplate` and `ChatClient` that takes a database schema and a natural language question, defends against prompt injection, applies few-shot learning exemplars, and generates ANSI SQL with explanations.

## Requirements
1. **Prompt Template Parameterization**: Use `{schema}`, `{dialect}`, and `{question}` variables.
2. **Defensive Guardrails**: Reject or neutralize adversarial prompts (`ignore previous instructions`, etc.).
3. **Few-Shot Exemplars**: Dynamically inject few-shot exemplars into user prompts.
4. **Automated Testing**: 100% test coverage using `MockChatModel`.
