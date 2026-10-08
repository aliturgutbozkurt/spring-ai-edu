package com.springai.edu.module09.controller;

import com.springai.edu.module09.client.McpClientGatewayService;
import com.springai.edu.module09.model.McpToolCallRequest;
import com.springai.edu.module09.model.McpToolCallResponse;
import com.springai.edu.module09.model.McpToolDescriptor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mcp")
public class McpGatewayController {

    private final McpClientGatewayService clientGateway;

    public McpGatewayController(McpClientGatewayService clientGateway) {
        this.clientGateway = clientGateway;
    }

    @GetMapping("/tools")
    public ResponseEntity<List<McpToolDescriptor>> listAvailableTools() {
        return ResponseEntity.ok(clientGateway.discoverTools());
    }

    @PostMapping("/call")
    public ResponseEntity<McpToolCallResponse> callTool(@Valid @RequestBody McpToolCallRequest request) {
        McpToolCallResponse response = clientGateway.executeRemoteTool(request.toolName(), request.arguments());
        return ResponseEntity.ok(response);
    }
}
