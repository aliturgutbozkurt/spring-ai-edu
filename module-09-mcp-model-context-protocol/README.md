# Module 09: Model Context Protocol (MCP)

Welcome to **Module 09** of the Spring AI Educational Series. This module teaches you how to implement Anthropic's Model Context Protocol (MCP) in Spring Boot, building both standardized enterprise tool servers and dynamic Spring AI clients.

## Key Features
- **MCP Server Architecture**: Exposing internal enterprise systems (ERP, CRM) via standardized MCP tool schemas.
- **MCP Client Gateway**: Discovering remote tool descriptors dynamically and invoking remote functions.
- **Security Sandboxing**: Granular role-based access control (`ROLE_ADMIN`, `ROLE_STAFF`, `ROLE_GUEST`) on tool execution.
- **Bilingual Documentation & Marp Slides**: Fully compiled PDF notes in English and Turkish.

## Running Tests
```bash
mvn clean test -pl module-09-mcp-model-context-protocol -am
```

## Running Homework
```bash
# Starter (fails on unimplemented TODOs):
mvn test -f module-09-mcp-model-context-protocol/homework/starter/pom.xml

# Solution (100% passing tests):
mvn test -f module-09-mcp-model-context-protocol/homework/solution/pom.xml
```
