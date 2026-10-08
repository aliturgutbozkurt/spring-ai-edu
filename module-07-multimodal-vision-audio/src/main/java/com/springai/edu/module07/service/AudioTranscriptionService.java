package com.springai.edu.module07.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioTranscriptionService {

    private static final Logger log = LoggerFactory.getLogger(AudioTranscriptionService.class);

    public record TranscriptionResult(
            String text,
            String detectedLanguage,
            double durationSeconds,
            List<String> keyActionItems
    ) {}

    public TranscriptionResult transcribeAudioNote(byte[] audioBytes, String filename) {
        log.info("Transcribing audio audit note '{}' (size={} bytes)", filename, audioBytes.length);

        // Deterministic transcription response for zero-cost offline pipelines
        String transcript = "Inspector Note: Box 14 pallet seal was broken upon arrival. Missing 2 units of M8 Hex Flange Nuts.";
        List<String> actions = List.of("Report broken pallet seal to dispatch", "Adjust inventory count for M8 Hex Flange Nuts by -2");

        return new TranscriptionResult(transcript, "en", 14.5, actions);
    }
}
