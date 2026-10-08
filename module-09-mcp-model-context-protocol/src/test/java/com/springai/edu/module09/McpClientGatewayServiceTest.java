package com.springai.edu.module09;

import com.springai.edu.module09.client.McpClientGatewayService;
import com.springai.edu.module09.model.McpToolCallResponse;
import com.springai.edu.module09.server.EnterpriseErpMcpServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class McpClientGatewayServiceTest {

    private final EnterpriseErpMcpServer mcpServer = new EnterpriseErpMcpServer();
    private final McpClientGatewayService clientGateway = new McpClientGatewayService(mcpServer);

    @Test
    @DisplayName("Should discover tools and execute vendor credit check")
    void shouldDiscoverAndExecute() {
        var tools = clientGateway.discoverTools();
        assertThat(tools).isNotEmpty();

        McpToolCallResponse response = clientGateway.executeRemoteTool(
                "getVendorCreditStatus",
                Map.of("vendorId", "VEND-992")
        );

        assertThat(response.success()).isTrue();
        assertThat(response.result()).containsEntry("rating", "AAA");
    }
}
