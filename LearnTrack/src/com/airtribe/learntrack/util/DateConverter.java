package com.airtribe.learntrack.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static String convertToDate(Instant enrollmentDate){
        String date = DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault())
                .format(enrollmentDate);
        return date;
    }
}
