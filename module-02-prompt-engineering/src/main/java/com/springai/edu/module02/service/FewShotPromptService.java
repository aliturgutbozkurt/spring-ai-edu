package com.springai.edu.module02.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Manages few-shot exemplars and in-context learning patterns for structured generation.
 */
@Service
public class FewShotPromptService {

    public record SqlExemplar(String question, String sql, String explanation) {}

    private final List<SqlExemplar> defaultExemplars = List.of(
            new SqlExemplar(
                    "Find the top 5 customers who spent the most money in 2025",
                    "SELECT c.id, c.name, SUM(o.total_amount) AS total_spent FROM customers c JOIN orders o ON c.id = o.customer_id WHERE o.order_date >= '2025-01-01' AND o.order_date <= '2025-12-31' GROUP BY c.id, c.name ORDER BY total_spent DESC LIMIT 5;",
                    "Aggregates order totals for 2025 per customer and sorts descending to return the top 5."
            ),
            new SqlExemplar(
                    "List all products that have never been ordered",
                    "SELECT p.id, p.name FROM products p LEFT JOIN order_items oi ON p.id = oi.product_id WHERE oi.product_id IS NULL;",
                    "Performs an outer join from products to order_items and filters for null foreign keys."
            )
    );

    public String buildFewShotSection() {
        StringBuilder sb = new StringBuilder("Few-Shot Examples:\n\n");
        for (int i = 0; i < defaultExemplars.size(); i++) {
            SqlExemplar ex = defaultExemplars.get(i);
            sb.append("Example ").append(i + 1).append(":\n");
            sb.append("User Question: ").append(ex.question()).append("\n");
            sb.append("SQL: ").append(ex.sql()).append("\n");
            sb.append("Explanation: ").append(ex.explanation()).append("\n\n");
        }
        return sb.toString();
    }
}
