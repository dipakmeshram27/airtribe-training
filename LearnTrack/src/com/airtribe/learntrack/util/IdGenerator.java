package com.airtribe.learntrack.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {

    private static final Map<String, AtomicInteger> COUNTERS_STU = new ConcurrentHashMap<>();
    private static final Map<String, AtomicInteger> COUNTERS_COURSE = new ConcurrentHashMap<>();
    private static final Map<String, AtomicInteger> COUNTERS_ENROLL = new ConcurrentHashMap<>();

    private IdGenerator() {
    }

    public static String getNextStudentId(String prefix) {
        int n = COUNTERS_STU.computeIfAbsent(prefix, k -> new AtomicInteger()).incrementAndGet();
        return String.format("%s-%03d", prefix, n);
    }
    public static String getNextCourseId(String prefix) {
        int n = COUNTERS_COURSE.computeIfAbsent(prefix, k -> new AtomicInteger()).incrementAndGet();
        return String.format("%s-%03d", prefix, n);
    }
    public static String getNextEnrollmentId(String prefix) {
        int n = COUNTERS_ENROLL.computeIfAbsent(prefix, k -> new AtomicInteger()).incrementAndGet();
        return String.format("%s-%03d", prefix, n);
    }
}
