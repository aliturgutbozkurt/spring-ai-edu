package com.springai.edu.module03.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.List;

public record CandidateProfile(
        @JsonPropertyDescription("Full name of the candidate")
        String fullName,

        @JsonPropertyDescription("Primary contact email address")
        String email,

        @JsonPropertyDescription("Executive professional summary")
        String summary,

        @JsonPropertyDescription("List of verified technical skills")
        List<CandidateSkill> skills,

        @JsonPropertyDescription("Chronological employment history")
        List<CandidateExperience> experiences
) {}
