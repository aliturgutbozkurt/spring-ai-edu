package com.springai.edu.homework08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonalTutorManagerTest {

    private final PersonalTutorManager tutorManager = new PersonalTutorManager();

    @Test
    @DisplayName("Should record and maintain conversational turns per student")
    void shouldRecordAndMaintainTurns() {
        String student = "student-101";
        tutorManager.recordTurn(student, "What is polymorphism?", "Polymorphism allows objects to be treated as instances of their parent class.");

        assertThat(tutorManager.getHistorySize(student)).isEqualTo(2);

        tutorManager.recordTurn(student, "Can you give a Java example?", "Certainly! Animal a = new Dog();");
        assertThat(tutorManager.getHistorySize(student)).isEqualTo(4);

        tutorManager.clearSession(student);
        assertThat(tutorManager.getHistorySize(student)).isEqualTo(0);
    }
}
