package com.springai.edu.module10.service;

import com.springai.edu.module10.model.AgentExecutionReport;
import com.springai.edu.module10.model.AgentStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReActAgentEngine {

    private static final Logger log = LoggerFactory.getLogger(ReActAgentEngine.class);
    private static final int MAX_ITERATIONS = 5;

    public AgentExecutionReport executeGoal(String goal) {
        long start = System.currentTimeMillis();
        String taskId = "TSK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        log.info("Agent [{}]: Starting ReAct loop for goal: '{}'", taskId, goal);

        List<AgentStep> steps = new ArrayList<>();
        int iteration = 1;
        String finalOutcome = "";

        // Simulated ReAct trajectory
        steps.add(new AgentStep(1,
                "Goal is: '" + goal + "'. I need to search the database for current market competitors.",
                "Tool: searchCompetitors(query='cloud native AI')",
                "Observation: Found 3 top competitors: VendorA, VendorB, VendorC."
        ));

        steps.add(new AgentStep(2,
                "Competitor data retrieved. I need to calculate feature overlap scores.",
                "Tool: analyzeFeatureMatrix(competitors=['VendorA', 'VendorB', 'VendorC'])",
                "Observation: Feature overlap score is 68% with competitive pricing differentiator."
        ));

        steps.add(new AgentStep(3,
                "All required information gathered. I can synthesize the final market research report.",
                "Tool: FINISH(status='SUCCESS')",
                "Observation: Final report synthesized with executive summary and actionable recommendations."
        ));

        finalOutcome = "Executive Report: Cloud native AI market has 3 primary rivals with 68% feature overlap. Recommended strategy is prioritizing zero-cost local inference.";

        long duration = System.currentTimeMillis() - start;
        log.info("Agent [{}]: Completed ReAct loop in {} steps ({}ms)", taskId, steps.size(), duration);

        return new AgentExecutionReport(taskId, goal, "COMPLETED", steps, finalOutcome, duration);
    }
}
