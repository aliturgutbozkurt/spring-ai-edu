package com.springai.edu.homework03;

import com.springai.edu.common.mock.MockChatModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResumeParserServiceTest {

    private ResumeParserService service;

    @BeforeEach
    void setUp() {
        String mockJson = """
                {
                  "name": "Linus Torvalds",
                  "email": "linus@kernel.org",
                  "experienceYears": 30,
                  "languages": ["C", "Rust", "Assembly"]
                }
                """;
        MockChatModel mockModel = new MockChatModel(mockJson);
        service = new ResumeParserService(mockModel);
    }

    @Test
    @DisplayName("Should extract typed ExtractedCandidate record")
    void shouldExtractCandidate() {
        var candidate = service.extractCandidate("Linus Torvalds, 30 years experience, created Linux in C.");

        assertNotNull(candidate);
        assertEquals("Linus Torvalds", candidate.name());
        assertEquals("linus@kernel.org", candidate.email());
        assertEquals(30, candidate.experienceYears());
        assertTrue(candidate.languages().contains("C"));
    }
}
