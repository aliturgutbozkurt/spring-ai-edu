package com.springai.edu.homework10;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MarketResearchSquad {

    public Map<String, String> executeResearchPlan(List<String> subTasks) {
        if (subTasks == null || subTasks.isEmpty()) {
            return Map.of();
        }
        return subTasks.stream()
                .collect(Collectors.toMap(
                        task -> task,
                        task -> "COMPLETED_BY_AGENT"
                ));
    }

    public boolean isApprovedByHuman(boolean humanApproval) {
        return humanApproval;
    }
}
