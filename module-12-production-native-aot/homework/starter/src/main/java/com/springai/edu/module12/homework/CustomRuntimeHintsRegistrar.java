package com.springai.edu.module12.homework;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * Homework Task:
 * Implement GraalVM Native AOT hints for the domain model and resource templates.
 */
public class CustomRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

    public record HomeworkAiReport(String summary, double score) {}

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // TODO: Register reflection hints for HomeworkAiReport (constructors, methods, fields)
        // TODO: Register resource pattern for "reports/*.json"
    }
}
