package com.springai.edu.module03;

import com.springai.edu.module03.model.CandidateProfile;
import com.springai.edu.module03.service.ResumeParserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.ai.ollama.chat.enabled=false"
})
class ResumeParserServiceTest {

    @Autowired
    private ResumeParserService resumeParserService;

    @Test
    @DisplayName("Should extract strongly-typed CandidateProfile record using BeanOutputConverter")
    void shouldExtractCandidateProfile() {
        String rawResume = """
                Sarah Connor
                Email: sarah.connor@cyberdyne.org
                Summary: Experienced defensive systems engineer with 8 years of experience.
                Skills: Java (Expert, 8 years), Spring Boot (Senior, 5 years), PostgreSQL (Senior, 6 years).
                Experience: Principal Systems Architect at Tech Defense Inc for 4 years.
                """;

        CandidateProfile profile = resumeParserService.parseResume(rawResume);

        assertNotNull(profile, "Extracted profile must not be null");
        assertEquals("Sarah Connor", profile.fullName());
        assertEquals("sarah.connor@cyberdyne.org", profile.email());
        assertFalse(profile.skills().isEmpty(), "Skills list must be extracted");
        assertEquals("Java", profile.skills().get(0).name());
        assertEquals("EXPERT", profile.skills().get(0).proficiencyLevel());
        assertEquals(8, profile.skills().get(0).yearsOfExperience());
        assertFalse(profile.experiences().isEmpty(), "Experiences list must be extracted");
        assertEquals("Tech Defense Inc", profile.experiences().get(0).company());
    }

    @Test
    @DisplayName("Should generate valid JSON Schema format instructions")
    void shouldGenerateJsonSchemaInstructions() {
        String format = resumeParserService.getJsonSchemaFormat();
        assertNotNull(format);
        assertTrue(format.contains("fullName"));
        assertTrue(format.contains("skills"));
    }
}
