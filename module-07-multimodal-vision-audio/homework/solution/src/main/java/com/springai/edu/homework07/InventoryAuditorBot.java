package com.springai.edu.homework07;

import java.util.List;

public class InventoryAuditorBot {

    public record AuditItem(String sku, int count, double pricePerUnit) {}

    public double computeTotalValue(List<AuditItem> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        return items.stream()
                .mapToDouble(i -> i.count() * i.pricePerUnit())
                .sum();
    }

    public boolean hasDiscrepancy(double recordedTotal, double calculatedTotal) {
        return Math.abs(recordedTotal - calculatedTotal) > 0.01;
    }
}
