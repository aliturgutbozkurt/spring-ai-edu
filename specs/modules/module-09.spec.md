# Specification: Module 09 - Model Context Protocol (MCP): Building Clients & Enterprise Servers

## 1. Overview
Module 09 covers Anthropic's Model Context Protocol (MCP) standardized protocol within Spring AI. Students build both Spring Boot MCP servers that expose secure tools/resources/prompts, and Spring AI MCP clients that discover and call tools dynamically over STDIO and SSE transports.

## 2. Learning Objectives
1. Understand MCP architecture: Host, Client, Server, Transports (STDIO vs SSE).
2. Build an enterprise Spring Boot MCP Server exposing internal enterprise ERP tools.
3. Configure Spring AI MCP Client with auto-discovery of tools.
4. Implement security, authentication, and permission sandboxing on MCP endpoints.
5. Provide offline deterministic testing for MCP tool invocations.

## 3. Architecture & Components
- **`McpToolDefinition`**: Java record describing tool name, description, schema, and handler.
- **`EnterpriseErpMcpServer`**: Exposes `/mcp/tools` (e.g. `queryInventory`, `getVendorCreditStatus`).
- **`McpClientGatewayService`**: Client that connects to the MCP server, downloads tool descriptors, and provides them to `ChatClient`.
- **`McpGatewayController`**: REST endpoint demonstrating client-to-server MCP interactions.

## 4. Quality Gates
- MCP tool registration and schema validation tests.
- Tool invocation roundtrip unit tests.
- Homework starter with failing stubs; solution with 100% passing tests.
- Bilingual lesson notes and Marp compiled PDFs.
