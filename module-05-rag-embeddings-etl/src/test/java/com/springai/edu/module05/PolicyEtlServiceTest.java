package com.springai.edu.module05;

import com.springai.edu.common.mock.MockEmbeddingModel;
import com.springai.edu.module05.service.PolicyEtlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyEtlServiceTest {

    private PolicyEtlService etlService;
    private VectorStore vectorStore;

    @BeforeEach
    void setUp() {
        vectorStore = SimpleVectorStore.builder(new MockEmbeddingModel(384)).build();
        etlService = new PolicyEtlService(vectorStore);
    }

    @Test
    @DisplayName("Should chunk raw policy text and ingest into vector store")
    void shouldChunkAndIngest() {
        String longText = """
                # Section 1: Security and Compliance
                All enterprise laptops must be encrypted using BitLocker or FileVault.
                Passwords must contain at least 15 characters including numbers, symbols, and uppercase letters.
                
                # Section 2: Remote Work Policy
                Employees must connect through the corporate WireGuard VPN when operating on public Wi-Fi networks.
                Screens must lock automatically after 3 minutes of inactivity.
                """;

        List<Document> chunks = etlService.chunkAndIngest(longText, "SecPolicy-2026.md", "Infosec");

        assertThat(chunks).isNotEmpty();
        assertThat(chunks.get(0).getMetadata()).containsEntry("source", "SecPolicy-2026.md");
        assertThat(chunks.get(0).getMetadata()).containsEntry("category", "Infosec");
    }
}
