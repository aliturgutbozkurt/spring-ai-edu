package com.springai.edu.capstone.controller;

import com.springai.edu.capstone.agent.EnterpriseSupervisorAgent;
import com.springai.edu.capstone.model.CapstoneRequest;
import com.springai.edu.capstone.model.CapstoneResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assistant")
public class EnterpriseAssistantController {

    private final EnterpriseSupervisorAgent supervisorAgent;

    public EnterpriseAssistantController(EnterpriseSupervisorAgent supervisorAgent) {
        this.supervisorAgent = supervisorAgent;
    }

    @PostMapping("/chat")
    public ResponseEntity<CapstoneResponse> chat(@Valid @RequestBody CapstoneRequest request) {
        CapstoneResponse response = supervisorAgent.processRequest(request);
        return ResponseEntity.ok(response);
    }
}
