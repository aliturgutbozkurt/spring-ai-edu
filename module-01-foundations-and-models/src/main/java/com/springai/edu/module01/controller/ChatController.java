package com.springai.edu.module01.controller;

import com.springai.edu.module01.dto.ChatPromptRequest;
import com.springai.edu.module01.dto.ChatPromptResponse;
import com.springai.edu.module01.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * REST controller demonstrating fluent ChatClient interactions and reactive SSE streaming.
 */
@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Synchronous prompt completion.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatPromptResponse chat(@Valid @RequestBody ChatPromptRequest request) {
        return chatService.generateResponse(request);
    }

    /**
     * Reactive Server-Sent Events (SSE) streaming endpoint.
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamChat(
            @RequestParam("prompt") String prompt,
            @RequestParam(value = "provider", required = false) String provider
    ) {
        return chatService.streamResponse(prompt, provider)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }
}
