package com.springai.edu.common.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MockChatModelTest {

    @Test
    @DisplayName("MockChatModel returns canned response when matched")
    void shouldReturnCannedResponse() {
        MockChatModel chatModel = new MockChatModel();
        chatModel.addCannedResponse("hello", "Hello, Spring AI learner!");

        var response = chatModel.call(new Prompt("Say hello to me"));
        assertThat(response.getResult().getOutput().getText()).isEqualTo("Hello, Spring AI learner!");
    }

    @Test
    @DisplayName("MockEmbeddingModel produces expected vector dimension")
    void shouldProduceNormalizedEmbeddings() {
        MockEmbeddingModel embeddingModel = new MockEmbeddingModel(384);
        EmbeddingResponse response = embeddingModel.call(new EmbeddingRequest(List.of("Spring AI RAG test"), null));

        assertThat(response.getResults()).hasSize(1);
        assertThat(response.getResults().get(0).getOutput()).hasSize(384);
    }
}
