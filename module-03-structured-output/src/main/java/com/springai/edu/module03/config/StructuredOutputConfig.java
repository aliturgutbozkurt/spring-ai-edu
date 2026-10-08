package com.springai.edu.module03.config;

import com.springai.edu.common.mock.MockChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StructuredOutputConfig {

    private static final String DETERMINISTIC_CANDIDATE_JSON = """
            {
              "fullName": "Sarah Connor",
              "email": "sarah.connor@cyberdyne.org",
              "summary": "Experienced defensive software systems engineer with 8 years of distributed systems expertise.",
              "skills": [
                {
                  "name": "Java",
                  "proficiencyLevel": "EXPERT",
                  "yearsOfExperience": 8
                },
                {
                  "name": "Spring Boot",
                  "proficiencyLevel": "SENIOR",
                  "yearsOfExperience": 5
                },
                {
                  "name": "PostgreSQL",
                  "proficiencyLevel": "SENIOR",
                  "yearsOfExperience": 6
                }
              ],
              "experiences": [
                {
                  "company": "Tech Defense Inc",
                  "role": "Principal Systems Architect",
                  "durationYears": 4,
                  "highlights": [
                    "Engineered real-time anomaly detection pipelines",
                    "Migrated monolith to Spring Boot microservices on Kubernetes"
                  ]
                }
              ]
            }
            """;

    @Bean(name = "mockChatModel")
    @Primary
    public MockChatModel mockChatModel() {
        return new MockChatModel(prompt -> DETERMINISTIC_CANDIDATE_JSON);
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatClient.Builder chatClientBuilder(ChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
