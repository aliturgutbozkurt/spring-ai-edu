package com.springai.edu.module12.homework;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class CustomRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

    public record HomeworkAiReport(String summary, double score) {}

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(
                HomeworkAiReport.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.DECLARED_FIELDS
        );

        hints.resources().registerPattern("reports/*.json");
    }
}
