package com.springai.edu.module03.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.List;

public record CandidateExperience(
        @JsonPropertyDescription("Company or organization name")
        String company,

        @JsonPropertyDescription("Role or job title (e.g. Senior Backend Engineer)")
        String role,

        @JsonPropertyDescription("Total duration in years")
        int durationYears,

        @JsonPropertyDescription("Key technical achievements or responsibilities")
        List<String> highlights
) {}
