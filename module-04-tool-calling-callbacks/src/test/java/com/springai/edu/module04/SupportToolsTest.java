package com.springai.edu.module04;

import com.springai.edu.module04.model.OrderRecord;
import com.springai.edu.module04.model.RefundDecision;
import com.springai.edu.module04.service.SupportTools;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupportToolsTest {

    private final SupportTools tools = new SupportTools();

    @Test
    @DisplayName("Should retrieve existing order details")
    void shouldRetrieveOrderDetails() {
        OrderRecord order = tools.getOrderDetails("ORD-101");
        assertNotNull(order);
        assertEquals("ORD-101", order.orderId());
        assertEquals(149.99, order.amount());
        assertEquals("DELIVERED", order.status());
    }

    @Test
    @DisplayName("Should approve valid refund for delivered order")
    void shouldApproveRefund() {
        RefundDecision decision = tools.calculateRefund("ORD-101", 149.99, "Damaged packaging");
        assertTrue(decision.approved());
        assertEquals(149.99, decision.refundAmount());
    }

    @Test
    @DisplayName("Should reject refund for order in transit")
    void shouldRejectRefundForInTransitOrder() {
        RefundDecision decision = tools.calculateRefund("ORD-102", 49.50, "Changed mind");
        assertFalse(decision.approved());
        assertTrue(decision.reason().contains("in transit"));
    }
}
