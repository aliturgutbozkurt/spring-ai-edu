package com.springai.edu.module05;

import com.springai.edu.common.mock.MockChatModel;
import com.springai.edu.common.mock.MockEmbeddingModel;
import com.springai.edu.module05.model.PolicyAnswerResponse;
import com.springai.edu.module05.model.PolicyQueryRequest;
import com.springai.edu.module05.service.PolicyEtlService;
import com.springai.edu.module05.service.PolicyQaService;
import com.springai.edu.module05.service.PolicySearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyQaServiceTest {

    private PolicyQaService qaService;
    private PolicyEtlService etlService;

    @BeforeEach
    void setUp() {
        VectorStore vectorStore = SimpleVectorStore.builder(new MockEmbeddingModel(384)).build();
        etlService = new PolicyEtlService(vectorStore);
        PolicySearchService searchService = new PolicySearchService(vectorStore);

        ChatClient chatClient = ChatClient.builder(
                new MockChatModel("Per corporate policy, full disk encryption and 15 character passwords are mandatory.")
        ).build();

        qaService = new PolicyQaService(searchService, chatClient);

        etlService.chunkAndIngest(
                "Corporate Security Guideline: Mandatory 15 char password and disk encryption on all employee laptops.",
                "Policy.txt",
                "IT"
        );
    }

    @Test
    @DisplayName("Should answer query using retrieved policy context")
    void shouldAnswerQueryWithContext() {
        PolicyQueryRequest request = new PolicyQueryRequest("What are password requirements?", 3, 0.0);

        PolicyAnswerResponse response = qaService.answerQuestion(request);

        assertThat(response).isNotNull();
        assertThat(response.answer()).contains("15 character passwords");
        assertThat(response.sourceSnippets()).isNotEmpty();
    }
}
