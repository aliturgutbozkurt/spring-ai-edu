package com.springai.edu.homework08;

public class PersonalTutorManager {

    public record Turn(String role, String text, long timestamp) {}

    public void recordTurn(String studentId, String userQuestion, String tutorAnswer) {
        // TODO: Store user question and tutor answer into the student's conversation history
        throw new UnsupportedOperationException("TODO: Implement recordTurn");
    }

    public int getHistorySize(String studentId) {
        // TODO: Return current number of turns/messages for student
        throw new UnsupportedOperationException("TODO: Implement getHistorySize");
    }

    public void clearSession(String studentId) {
        // TODO: Remove session from memory store
        throw new UnsupportedOperationException("TODO: Implement clearSession");
    }
}
