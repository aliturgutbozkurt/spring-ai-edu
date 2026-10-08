package com.springai.edu.homework09;

import java.util.Map;
import java.util.Set;

public class McpSecuritySandbox {

    private static final Map<String, Set<String>> ROLE_PERMISSIONS = Map.of(
            "ROLE_ADMIN", Set.of("queryInventory", "updateInventory", "approveRefund"),
            "ROLE_STAFF", Set.of("queryInventory", "updateInventory"),
            "ROLE_GUEST", Set.of("queryInventory")
    );

    public boolean authorizeToolCall(String userRole, String toolName) {
        if (userRole == null || toolName == null) {
            return false;
        }
        Set<String> allowedTools = ROLE_PERMISSIONS.getOrDefault(userRole, Set.of());
        return allowedTools.contains(toolName);
    }
}
