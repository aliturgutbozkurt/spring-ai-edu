package com.springai.edu.module07.service;

import com.springai.edu.module07.model.AuditReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

@Service
public class MultimodalVisionService {

    private static final Logger log = LoggerFactory.getLogger(MultimodalVisionService.class);

    private final ChatClient chatClient;

    public MultimodalVisionService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public AuditReport analyzeInventoryReceipt(byte[] imageBytes, MimeType mimeType, String auditInstruction) {
        log.info("Analyzing receipt image (size={} bytes, mimeType={})", imageBytes.length, mimeType);

        Media media = new Media(mimeType, new ByteArrayResource(imageBytes));

        return chatClient.prompt()
                .user(u -> u.text("Inspect this warehouse inventory receipt. " + auditInstruction)
                        .media(media))
                .call()
                .entity(AuditReport.class);
    }
}
