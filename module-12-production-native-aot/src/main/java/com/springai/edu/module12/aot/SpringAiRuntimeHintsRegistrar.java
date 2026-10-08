package com.springai.edu.module12.aot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class SpringAiRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

    private static final Logger log = LoggerFactory.getLogger(SpringAiRuntimeHintsRegistrar.class);

    public record NativeModelDescriptor(String modelName, String provider, int maxTokens) {}

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        log.info("Registering Spring AI GraalVM Native AOT Reflection and Resource Hints...");

        // Register reflection hints for model serialization classes
        hints.reflection().registerType(
                NativeModelDescriptor.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.DECLARED_FIELDS
        );

        // Register resources for prompts and schemas
        hints.resources().registerPattern("prompts/*.st");
        hints.resources().registerPattern("documents/*.md");
    }
}
