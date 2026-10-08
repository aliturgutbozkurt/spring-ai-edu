package com.springai.edu.homework08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersonalTutorManager {

    public record Turn(String role, String text, long timestamp) {}

    private final Map<String, List<Turn>> sessions = new ConcurrentHashMap<>();

    public void recordTurn(String studentId, String userQuestion, String tutorAnswer) {
        sessions.computeIfAbsent(studentId, k -> Collections.synchronizedList(new ArrayList<>()));
        List<Turn> turns = sessions.get(studentId);
        long now = System.currentTimeMillis();
        turns.add(new Turn("user", userQuestion, now));
        turns.add(new Turn("assistant", tutorAnswer, now));
    }

    public int getHistorySize(String studentId) {
        return sessions.getOrDefault(studentId, List.of()).size();
    }

    public void clearSession(String studentId) {
        sessions.remove(studentId);
    }
}
