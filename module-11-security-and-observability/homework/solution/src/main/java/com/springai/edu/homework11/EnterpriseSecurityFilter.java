package com.springai.edu.homework11;

import java.util.regex.Pattern;

public class EnterpriseSecurityFilter {

    private static final Pattern PHONE_PATTERN = Pattern.compile("\\b\\d{3}-\\d{3}-\\d{4}\\b");

    public String maskPhoneNumbers(String text) {
        if (text == null) {
            return "";
        }
        return PHONE_PATTERN.matcher(text).replaceAll("[REDACTED_PHONE]");
    }

    public boolean hasSecurityRisk(String text) {
        if (text == null || text.isBlank()) {
            return false;
        }
        String lower = text.toLowerCase();
        return lower.contains("drop table") || lower.contains("ignore system prompt") || lower.contains("bypass security");
    }
}
