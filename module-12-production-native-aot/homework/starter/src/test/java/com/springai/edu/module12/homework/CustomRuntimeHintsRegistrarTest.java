package com.springai.edu.module12.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Module 12 Homework Starter - RuntimeHints Tests")
class CustomRuntimeHintsRegistrarTest {

    @Test
    @DisplayName("Should have registered reflection and resource patterns in RuntimeHints")
    void shouldRegisterHints() {
        CustomRuntimeHintsRegistrar registrar = new CustomRuntimeHintsRegistrar();
        RuntimeHints hints = new RuntimeHints();

        registrar.registerHints(hints, getClass().getClassLoader());

        assertThat(hints.reflection().typeHints())
                .as("Reflection hint for HomeworkAiReport must be registered")
                .anyMatch(t -> t.getType().getName().contains("HomeworkAiReport"));

        boolean hasReportsHint = hints.resources().resourcePatternHints()
                .flatMap(rph -> rph.getIncludes().stream())
                .anyMatch(inc -> inc.getPattern().contains("reports/*.json"));

        assertThat(hasReportsHint)
                .as("Resource pattern for reports/*.json must be registered")
                .isTrue();
    }
}
