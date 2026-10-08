package com.springai.edu.module10.controller;

import com.springai.edu.module10.model.AgentExecutionReport;
import com.springai.edu.module10.service.MultiAgentSupervisorService;
import com.springai.edu.module10.service.ReActAgentEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentOrchestrationController {

    private final ReActAgentEngine agentEngine;
    private final MultiAgentSupervisorService supervisorService;

    public AgentOrchestrationController(ReActAgentEngine agentEngine, MultiAgentSupervisorService supervisorService) {
        this.agentEngine = agentEngine;
        this.supervisorService = supervisorService;
    }

    @PostMapping("/execute-goal")
    public ResponseEntity<AgentExecutionReport> executeGoal(@RequestParam String goal) {
        AgentExecutionReport report = agentEngine.executeGoal(goal);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/supervisor/delegate")
    public ResponseEntity<Map<String, Object>> delegate(@RequestParam String mission) {
        Map<String, Object> result = supervisorService.delegateComplexProject(mission);
        return ResponseEntity.ok(result);
    }
}
