package com.springai.edu.homework04;

import com.springai.edu.common.mock.MockChatModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderRefundServiceTest {

    private OrderRefundService service;

    @BeforeEach
    void setUp() {
        MockChatModel mockModel = new MockChatModel("Refund has been processed and approved.");
        service = new OrderRefundService(mockModel);
    }

    @Test
    @DisplayName("Should evaluate eligibility correctly")
    void shouldEvaluateEligibility() {
        assertEquals("ELIGIBLE", service.checkEligibility("ORD-99", 150.0));
        assertEquals("EXCEEDS_LIMIT", service.checkEligibility("ORD-99", 250.0));
    }

    @Test
    @DisplayName("Should process refund request using tool calling")
    void shouldProcessRefundRequest() {
        String response = service.processRefundRequest("ORD-99", 150.0, "I want a refund for ORD-99");
        assertNotNull(response);
        assertTrue(response.contains("Refund has been processed"));
    }
}
