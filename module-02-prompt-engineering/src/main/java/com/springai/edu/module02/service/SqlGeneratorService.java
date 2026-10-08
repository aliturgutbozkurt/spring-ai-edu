package com.springai.edu.module02.service;

import com.springai.edu.module02.config.PromptConfig;
import com.springai.edu.module02.dto.SqlGenerationRequest;
import com.springai.edu.module02.dto.SqlGenerationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service demonstrating resource-based PromptTemplate, dynamic context injection,
 * and few-shot exemplar composition in Spring AI.
 */
@Service
public class SqlGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(SqlGeneratorService.class);

    private final ChatClient chatClient;
    private final PromptConfig promptConfig;
    private final FewShotPromptService fewShotPromptService;
    private final PromptInjectionSanitizer sanitizer;

    private static final String DEFAULT_SCHEMA = """
            CREATE TABLE customers (id INT PRIMARY KEY, name VARCHAR(100), email VARCHAR(100), active BOOLEAN);
            CREATE TABLE orders (id INT PRIMARY KEY, customer_id INT, total_amount DECIMAL(10,2), order_date DATE);
            CREATE TABLE order_items (id INT PRIMARY KEY, order_id INT, product_id INT, quantity INT, unit_price DECIMAL(10,2));
            CREATE TABLE products (id INT PRIMARY KEY, name VARCHAR(100), category VARCHAR(50), price DECIMAL(10,2));
            """;

    public SqlGeneratorService(ChatClient chatClient,
                               PromptConfig promptConfig,
                               FewShotPromptService fewShotPromptService,
                               PromptInjectionSanitizer sanitizer) {
        this.chatClient = chatClient;
        this.promptConfig = promptConfig;
        this.fewShotPromptService = fewShotPromptService;
        this.sanitizer = sanitizer;
    }

    public SqlGenerationResponse generateSql(SqlGenerationRequest request) {
        long startTime = System.currentTimeMillis();

        // 1. Guard against prompt injection
        boolean hasInjection = sanitizer.detectInjection(request.question());
        if (hasInjection) {
            log.warn("Blocking request due to detected prompt injection");
            long duration = System.currentTimeMillis() - startTime;
            return new SqlGenerationResponse(
                    "SECURITY_ALERT: Query generation cancelled due to detected adversarial prompt pattern.",
                    "The prompt was rejected by the prompt sanitizer.",
                    true,
                    duration
            );
        }

        // 2. Resolve database schema
        String activeSchema = (request.schema() != null && !request.schema().isBlank())
                ? request.schema()
                : DEFAULT_SCHEMA;

        // 3. Render System Prompt from template resource
        PromptTemplate systemTemplate = new PromptTemplate(promptConfig.getSqlSystemPromptResource());
        String renderedSystemPrompt = systemTemplate.render(Map.of(
                "schema", activeSchema,
                "dialect", request.dialect() != null ? request.dialect() : "ansi"
        ));

        // 4. Assemble Few-Shot context + User question
        String fewShotSection = fewShotPromptService.buildFewShotSection();
        String fullUserPrompt = fewShotSection + "Now generate SQL for this question:\n" + request.question();

        // 5. Execute via fluent ChatClient
        String rawOutput = chatClient.prompt()
                .system(renderedSystemPrompt)
                .user(fullUserPrompt)
                .call()
                .content();

        long duration = System.currentTimeMillis() - startTime;

        // 6. Parse SQL and Explanation
        String sql = rawOutput;
        String explanation = "Generated from provided schema.";
        if (rawOutput != null && rawOutput.contains("Explanation:")) {
            String[] parts = rawOutput.split("Explanation:", 2);
            sql = parts[0].trim();
            explanation = parts[1].trim();
        }

        return new SqlGenerationResponse(sql, explanation, false, duration);
    }
}
