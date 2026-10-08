package com.springai.edu.module03.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CandidateSkill(
        @JsonPropertyDescription("Name of the technical skill or technology (e.g. Java, Spring Boot, PostgreSQL)")
        String name,

        @JsonPropertyDescription("Proficiency level: JUNIOR, MID, SENIOR, EXPERT")
        String proficiencyLevel,

        @JsonPropertyDescription("Number of years of hands-on experience with this skill")
        int yearsOfExperience
) {}
