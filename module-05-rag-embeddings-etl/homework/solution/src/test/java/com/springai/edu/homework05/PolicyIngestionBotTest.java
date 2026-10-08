package com.springai.edu.homework05;

import com.springai.edu.common.mock.MockEmbeddingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyIngestionBotTest {

    private PolicyIngestionBot bot;
    private VectorStore vectorStore;

    @BeforeEach
    void setUp() {
        vectorStore = SimpleVectorStore.builder(new MockEmbeddingModel(384)).build();
        bot = new PolicyIngestionBot(vectorStore);
    }

    @Test
    @DisplayName("Should ingest and partition document into chunks")
    void shouldIngestAndChunk() {
        String engineeringPolicy = "All code committed to main branch must pass unit tests and static analysis. Deployments require two approvals.";
        List<Document> chunks = bot.ingestPolicy(engineeringPolicy, "Engineering", "ENG-001");

        assertThat(chunks).isNotEmpty();
        assertThat(chunks.get(0).getMetadata().get("department")).isEqualTo("Engineering");
    }

    @Test
    @DisplayName("Should retrieve documents matching metadata filter")
    void shouldSearchWithFilter() {
        bot.ingestPolicy("Engineers must use MFA keys.", "Engineering", "ENG-002");
        bot.ingestPolicy("Sales representatives must log all customer calls.", "Sales", "SAL-001");

        List<Document> results = bot.searchByDepartment("security credentials", "Engineering", 2);
        assertThat(results).isNotEmpty();
        for (Document doc : results) {
            assertThat(doc.getMetadata().get("department")).isEqualTo("Engineering");
        }
    }
}
