package com.springai.edu.module09.server;

import com.springai.edu.module09.model.McpToolCallRequest;
import com.springai.edu.module09.model.McpToolCallResponse;
import com.springai.edu.module09.model.McpToolDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EnterpriseErpMcpServer {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseErpMcpServer.class);

    private final Map<String, McpToolDescriptor> registeredTools = new ConcurrentHashMap<>();

    public EnterpriseErpMcpServer() {
        registerBuiltinTools();
    }

    private void registerBuiltinTools() {
        registeredTools.put("queryInventory", new McpToolDescriptor(
                "queryInventory",
                "Queries real-time warehouse inventory for a given SKU",
                Map.of("type", "object", "properties", Map.of("sku", Map.of("type", "string")), "required", List.of("sku"))
        ));

        registeredTools.put("getVendorCreditStatus", new McpToolDescriptor(
                "getVendorCreditStatus",
                "Retrieves credit approval rating and payment balance for a supplier/vendor ID",
                Map.of("type", "object", "properties", Map.of("vendorId", Map.of("type", "string")), "required", List.of("vendorId"))
        ));
    }

    public List<McpToolDescriptor> listTools() {
        return List.copyOf(registeredTools.values());
    }

    public McpToolCallResponse callTool(McpToolCallRequest request) {
        long start = System.currentTimeMillis();
        String name = request.toolName();

        if (!registeredTools.containsKey(name)) {
            return McpToolCallResponse.failure(name, "Unknown MCP tool: " + name, System.currentTimeMillis() - start);
        }

        try {
            Map<String, Object> output = switch (name) {
                case "queryInventory" -> {
                    String sku = String.valueOf(request.arguments().getOrDefault("sku", "UNKNOWN"));
                    yield Map.of("sku", sku, "inStock", 450, "warehouse", "WH-FRANKFURT-01", "reorderThreshold", 100);
                }
                case "getVendorCreditStatus" -> {
                    String vendorId = String.valueOf(request.arguments().getOrDefault("vendorId", "UNKNOWN"));
                    yield Map.of("vendorId", vendorId, "rating", "AAA", "creditLimit", 250000.0, "currentBalance", 34200.0);
                }
                default -> throw new IllegalArgumentException("Unsupported tool: " + name);
            };

            return McpToolCallResponse.success(name, output, System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.error("Error executing MCP tool '{}': {}", name, e.getMessage());
            return McpToolCallResponse.failure(name, e.getMessage(), System.currentTimeMillis() - start);
        }
    }
}
