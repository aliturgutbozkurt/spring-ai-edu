package com.springai.edu.homework07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InventoryAuditorBotTest {

    private final InventoryAuditorBot auditor = new InventoryAuditorBot();

    @Test
    @DisplayName("Should compute total value of audited items")
    void shouldComputeTotalValue() {
        var items = List.of(
                new InventoryAuditorBot.AuditItem("SKU-1", 5, 20.0),
                new InventoryAuditorBot.AuditItem("SKU-2", 2, 50.0)
        );

        double total = auditor.computeTotalValue(items);
        assertThat(total).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Should detect discrepancy when recorded total diverges from calculated total")
    void shouldDetectDiscrepancy() {
        boolean mismatch = auditor.hasDiscrepancy(250.0, 200.0);
        boolean match = auditor.hasDiscrepancy(200.0, 200.0);

        assertThat(mismatch).isTrue();
        assertThat(match).isFalse();
    }
}
