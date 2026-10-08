package com.springai.edu.module07.controller;

import com.springai.edu.module07.model.AuditReport;
import com.springai.edu.module07.service.AudioTranscriptionService;
import com.springai.edu.module07.service.MultimodalVisionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/multimodal")
public class MultimodalAuditController {

    private final MultimodalVisionService visionService;
    private final AudioTranscriptionService audioService;

    public MultimodalAuditController(MultimodalVisionService visionService, AudioTranscriptionService audioService) {
        this.visionService = visionService;
        this.audioService = audioService;
    }

    @PostMapping(value = "/audit-receipt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuditReport> auditReceipt(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "instructions", defaultValue = "Extract all line items and flag discrepancies") String instructions
    ) throws IOException {
        String contentType = file.getContentType() != null ? file.getContentType() : "image/jpeg";
        MimeType mimeType = MimeTypeUtils.parseMimeType(contentType);

        AuditReport report = visionService.analyzeInventoryReceipt(file.getBytes(), mimeType, instructions);
        return ResponseEntity.ok(report);
    }

    @PostMapping(value = "/transcribe-audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AudioTranscriptionService.TranscriptionResult> transcribe(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        var result = audioService.transcribeAudioNote(file.getBytes(), file.getOriginalFilename());
        return ResponseEntity.ok(result);
    }
}
