package com.springai.edu.module09.client;

import com.springai.edu.module09.model.McpToolCallRequest;
import com.springai.edu.module09.model.McpToolCallResponse;
import com.springai.edu.module09.model.McpToolDescriptor;
import com.springai.edu.module09.server.EnterpriseErpMcpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class McpClientGatewayService {

    private static final Logger log = LoggerFactory.getLogger(McpClientGatewayService.class);

    private final EnterpriseErpMcpServer mcpServer;

    public McpClientGatewayService(EnterpriseErpMcpServer mcpServer) {
        this.mcpServer = mcpServer;
    }

    public List<McpToolDescriptor> discoverTools() {
        log.info("MCP Client: discovering available tools from MCP Server...");
        return mcpServer.listTools();
    }

    public McpToolCallResponse executeRemoteTool(String toolName, Map<String, Object> arguments) {
        log.info("MCP Client: invoking tool '{}' with arguments {}", toolName, arguments);
        return mcpServer.callTool(new McpToolCallRequest(toolName, arguments));
    }
}
