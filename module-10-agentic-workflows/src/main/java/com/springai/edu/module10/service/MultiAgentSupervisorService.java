package com.springai.edu.module10.service;

import com.springai.edu.module10.model.AgentExecutionReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MultiAgentSupervisorService {

    private static final Logger log = LoggerFactory.getLogger(MultiAgentSupervisorService.class);

    private final ReActAgentEngine agentEngine;

    public MultiAgentSupervisorService(ReActAgentEngine agentEngine) {
        this.agentEngine = agentEngine;
    }

    public Map<String, Object> delegateComplexProject(String mission) {
        log.info("Supervisor Agent: Decomposing enterprise mission: '{}'", mission);

        // Step 1: Delegate research to ReAct agent
        AgentExecutionReport researchReport = agentEngine.executeGoal("Research competitors and market trends for: " + mission);

        // Step 2: Supervisor combines subagent deliverables
        return Map.of(
                "mission", mission,
                "status", "DELIVERED",
                "subagentReports", Map.of(
                        "MarketResearchAgent", researchReport.outcome(),
                        "ExecutionSteps", researchReport.steps().size()
                ),
                "approvalStatus", "APPROVED_BY_SUPERVISOR"
        );
    }
}
