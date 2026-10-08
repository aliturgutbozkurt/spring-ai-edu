package com.springai.edu.module04.controller;

import com.springai.edu.module04.model.TicketTriageResult;
import com.springai.edu.module04.service.CustomerSupportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller exposing autonomous ticket triage endpoints.
 */
@RestController
@RequestMapping("/api/v1/support")
public class SupportTicketController {

    private final CustomerSupportService supportService;

    public SupportTicketController(CustomerSupportService supportService) {
        this.supportService = supportService;
    }

    @PostMapping(value = "/triage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketTriageResult triageTicket(@RequestBody Map<String, String> request) {
        String inquiry = request.getOrDefault("inquiry", "General question");
        return supportService.triageTicket(inquiry);
    }
}
