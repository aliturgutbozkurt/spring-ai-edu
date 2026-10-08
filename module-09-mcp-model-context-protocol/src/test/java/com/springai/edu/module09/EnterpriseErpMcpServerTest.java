package com.springai.edu.module09;

import com.springai.edu.module09.model.McpToolCallRequest;
import com.springai.edu.module09.model.McpToolCallResponse;
import com.springai.edu.module09.server.EnterpriseErpMcpServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EnterpriseErpMcpServerTest {

    private final EnterpriseErpMcpServer mcpServer = new EnterpriseErpMcpServer();

    @Test
    @DisplayName("Should list registered enterprise ERP tools with schemas")
    void shouldListRegisteredTools() {
        var tools = mcpServer.listTools();
        assertThat(tools).isNotEmpty();
        assertThat(tools).extracting("name").contains("queryInventory", "getVendorCreditStatus");
    }

    @Test
    @DisplayName("Should execute queryInventory tool call successfully")
    void shouldExecuteToolCall() {
        McpToolCallRequest request = new McpToolCallRequest("queryInventory", Map.of("sku", "BOLT-M8"));
        McpToolCallResponse response = mcpServer.callTool(request);

        assertThat(response.success()).isTrue();
        assertThat(response.result()).containsEntry("sku", "BOLT-M8");
        assertThat(response.result()).containsEntry("inStock", 450);
    }
}
