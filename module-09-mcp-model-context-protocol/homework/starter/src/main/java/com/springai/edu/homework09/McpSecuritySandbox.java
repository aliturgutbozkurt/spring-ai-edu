package com.springai.edu.homework09;

public class McpSecuritySandbox {

    public boolean authorizeToolCall(String userRole, String toolName) {
        // TODO: Enforce role-based access control for MCP tools:
        // ROLE_ADMIN: all tools
        // ROLE_STAFF: queryInventory, updateInventory
        // ROLE_GUEST: queryInventory
        throw new UnsupportedOperationException("TODO: Implement authorizeToolCall");
    }
}
