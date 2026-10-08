# Homework 04: Autonomous Customer Support Ticket Triage Agent (English)

## Objective
Implement an autonomous tool calling system where an LLM calls a registered order lookup and refund evaluation tool using Spring AI's `@Tool` annotations and `ChatClient.tools(...)`.

## Requirements
1. Implement the tool method `checkEligibility(String orderId, double amount)`.
2. Bind the tool to `ChatClient`.
3. Provide automated unit tests verifying tool execution.
