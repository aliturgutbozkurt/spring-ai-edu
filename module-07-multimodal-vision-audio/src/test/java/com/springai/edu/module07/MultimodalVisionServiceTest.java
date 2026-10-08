package com.springai.edu.module07;

import com.springai.edu.common.mock.MockChatModel;
import com.springai.edu.module07.model.AuditReport;
import com.springai.edu.module07.service.MultimodalVisionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.util.MimeTypeUtils;

import static org.assertj.core.api.Assertions.assertThat;

class MultimodalVisionServiceTest {

    @Test
    @DisplayName("Should parse image receipt into strongly-typed AuditReport")
    void shouldParseReceiptImage() {
        String mockJsonResponse = """
                {
                  "supplierName": "Industrial Fasteners Corp",
                  "receiptNumber": "REC-9942",
                  "totalAmount": 482.50,
                  "items": [
                    {"sku": "BOLT-M8", "description": "Stainless Steel M8 Bolts (Box of 100)", "quantity": 5, "unitPrice": 42.50}
                  ],
                  "discrepanciesDetected": false,
                  "confidenceScore": 0.98
                }
                """;

        ChatClient chatClient = ChatClient.builder(new MockChatModel(mockJsonResponse)).build();
        MultimodalVisionService visionService = new MultimodalVisionService(chatClient);

        byte[] fakeImageBytes = new byte[]{1, 2, 3, 4, 5};
        AuditReport report = visionService.analyzeInventoryReceipt(fakeImageBytes, MimeTypeUtils.IMAGE_JPEG, "Audit items");

        assertThat(report).isNotNull();
        assertThat(report.supplierName()).isEqualTo("Industrial Fasteners Corp");
        assertThat(report.receiptNumber()).isEqualTo("REC-9942");
        assertThat(report.items()).hasSize(1);
        assertThat(report.items().get(0).sku()).isEqualTo("BOLT-M8");
    }
}
