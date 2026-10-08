package com.springai.edu.homework09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class McpSecuritySandboxTest {

    private final McpSecuritySandbox sandbox = new McpSecuritySandbox();

    @Test
    @DisplayName("Should permit authorized roles to execute appropriate MCP tools")
    void shouldAuthorizePermittedTools() {
        assertThat(sandbox.authorizeToolCall("ROLE_ADMIN", "approveRefund")).isTrue();
        assertThat(sandbox.authorizeToolCall("ROLE_STAFF", "updateInventory")).isTrue();
        assertThat(sandbox.authorizeToolCall("ROLE_GUEST", "queryInventory")).isTrue();
    }

    @Test
    @DisplayName("Should block unauthorized roles from invoking privileged tools")
    void shouldBlockUnauthorizedTools() {
        assertThat(sandbox.authorizeToolCall("ROLE_GUEST", "approveRefund")).isFalse();
        assertThat(sandbox.authorizeToolCall("ROLE_STAFF", "approveRefund")).isFalse();
    }
}
