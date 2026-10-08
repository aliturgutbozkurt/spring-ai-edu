package com.springai.edu.module08.controller;

import com.springai.edu.module08.model.TutorChatRequest;
import com.springai.edu.module08.model.TutorChatResponse;
import com.springai.edu.module08.service.TutorConversationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tutor")
public class TutorChatController {

    private final TutorConversationService tutorService;

    public TutorChatController(TutorConversationService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/chat")
    public ResponseEntity<TutorChatResponse> chat(@Valid @RequestBody TutorChatRequest request) {
        TutorChatResponse response = tutorService.chat(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/conversations/{conversationId}")
    public ResponseEntity<Map<String, String>> clearSession(@PathVariable String conversationId) {
        tutorService.resetConversation(conversationId);
        return ResponseEntity.ok(Map.of("status", "CLEARED", "conversationId", conversationId));
    }
}
