package com.springai.edu.module03.controller;

import com.springai.edu.module03.model.CandidateProfile;
import com.springai.edu.module03.service.ResumeParserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller exposing type-safe candidate parsing endpoints.
 */
@RestController
@RequestMapping("/api/v1/resumes")
public class ResumeParserController {

    private final ResumeParserService resumeParserService;

    public ResumeParserController(ResumeParserService resumeParserService) {
        this.resumeParserService = resumeParserService;
    }

    @PostMapping(value = "/parse", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidateProfile parseRawResume(@RequestBody String rawResume) {
        return resumeParserService.parseResume(rawResume);
    }

    @GetMapping(value = "/schema", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getSchema() {
        return resumeParserService.getJsonSchemaFormat();
    }
}
