package com.springai.edu.homework07;

import java.util.List;

public class InventoryAuditorBot {

    public record AuditItem(String sku, int count, double pricePerUnit) {}

    public double computeTotalValue(List<AuditItem> items) {
        // TODO: Compute sum of (count * pricePerUnit) across all items
        throw new UnsupportedOperationException("TODO: Implement computeTotalValue");
    }

    public boolean hasDiscrepancy(double recordedTotal, double calculatedTotal) {
        // TODO: Check if Math.abs(recordedTotal - calculatedTotal) > 0.01
        throw new UnsupportedOperationException("TODO: Implement hasDiscrepancy");
    }
}
