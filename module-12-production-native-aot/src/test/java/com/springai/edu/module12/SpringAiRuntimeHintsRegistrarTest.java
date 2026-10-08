package com.springai.edu.module12;

import com.springai.edu.module12.aot.SpringAiRuntimeHintsRegistrar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Module 12 - Spring AI GraalVM RuntimeHints Registrar Tests")
class SpringAiRuntimeHintsRegistrarTest {

    @Test
    @DisplayName("Should successfully register reflection and resource hints for GraalVM AOT")
    void shouldRegisterReflectionAndResourceHints() {
        SpringAiRuntimeHintsRegistrar registrar = new SpringAiRuntimeHintsRegistrar();
        RuntimeHints hints = new RuntimeHints();

        registrar.registerHints(hints, getClass().getClassLoader());

        // Verify reflection hint
        assertThat(hints.reflection().typeHints())
                .anyMatch(typeHint -> typeHint.getType().getName().contains("NativeModelDescriptor"));

        // Verify resource hints
        boolean hasPromptHint = hints.resources().resourcePatternHints()
                .flatMap(rph -> rph.getIncludes().stream())
                .anyMatch(inc -> inc.getPattern().contains("prompts/*.st"));
        assertThat(hasPromptHint).isTrue();

        boolean hasDocumentHint = hints.resources().resourcePatternHints()
                .flatMap(rph -> rph.getIncludes().stream())
                .anyMatch(inc -> inc.getPattern().contains("documents/*.md"));
        assertThat(hasDocumentHint).isTrue();
    }
}
