package com.springai.edu.module07;

import com.springai.edu.module07.service.AudioTranscriptionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AudioTranscriptionServiceTest {

    private final AudioTranscriptionService audioService = new AudioTranscriptionService();

    @Test
    @DisplayName("Should transcribe audio note and extract actionable inventory steps")
    void shouldTranscribeAudioNote() {
        byte[] fakeWavBytes = new byte[]{10, 20, 30, 40};
        var result = audioService.transcribeAudioNote(fakeWavBytes, "inspector-memo.wav");

        assertThat(result).isNotNull();
        assertThat(result.text()).contains("Inspector Note");
        assertThat(result.keyActionItems()).isNotEmpty();
        assertThat(result.detectedLanguage()).isEqualTo("en");
    }
}
