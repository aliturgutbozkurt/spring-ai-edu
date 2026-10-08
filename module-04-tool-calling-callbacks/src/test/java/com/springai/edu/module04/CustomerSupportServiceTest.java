package com.springai.edu.module04;

import com.springai.edu.module04.model.TicketTriageResult;
import com.springai.edu.module04.service.CustomerSupportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.ai.ollama.chat.enabled=false"
})
class CustomerSupportServiceTest {

    @Autowired
    private CustomerSupportService supportService;

    @Test
    @DisplayName("Should triage customer support ticket using tools")
    void shouldTriageSupportTicket() {
        TicketTriageResult result = supportService.triageTicket(
                "My order ORD-101 was damaged. I would like a refund of $149.99 please."
        );

        assertNotNull(result);
        assertNotNull(result.ticketId());
        assertTrue(result.ticketId().startsWith("TCK-"));
        assertNotNull(result.resolution());
        assertTrue(result.durationMs() >= 0);
    }
}
