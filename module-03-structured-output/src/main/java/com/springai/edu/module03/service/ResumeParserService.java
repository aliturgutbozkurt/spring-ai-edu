package com.springai.edu.module03.service;

import com.springai.edu.module03.model.CandidateProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

/**
 * Service demonstrating type-safe LLM extraction to Java records using BeanOutputConverter.
 */
@Service
public class ResumeParserService {

    private static final Logger log = LoggerFactory.getLogger(ResumeParserService.class);

    private final ChatClient chatClient;
    private final BeanOutputConverter<CandidateProfile> outputConverter;

    public ResumeParserService(ChatClient chatClient) {
        this.chatClient = chatClient;
        this.outputConverter = new BeanOutputConverter<>(CandidateProfile.class);
    }

    /**
     * Parses raw resume text into strongly-typed CandidateProfile record.
     */
    public CandidateProfile parseResume(String rawResumeText) {
        log.info("Parsing unstructured resume text (length: {} chars)", rawResumeText != null ? rawResumeText.length() : 0);

        String formatInstructions = outputConverter.getFormat();

        String rawResponse = chatClient.prompt()
                .system("You are a professional HR data extraction engine. Extract all candidate information accurately.\n" + formatInstructions)
                .user(rawResumeText != null ? rawResumeText : "")
                .call()
                .content();

        log.debug("Raw extraction output from LLM: {}", rawResponse);

        CandidateProfile profile = outputConverter.convert(rawResponse);
        log.info("Successfully extracted candidate: {} ({}) with {} skills and {} experiences",
                profile.fullName(), profile.email(), profile.skills().size(), profile.experiences().size());

        return profile;
    }

    public String getJsonSchemaFormat() {
        return outputConverter.getFormat();
    }
}
