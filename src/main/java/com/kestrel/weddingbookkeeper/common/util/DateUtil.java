package com.kestrel.weddingbookkeeper.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private DateUtil() {
    }

    public static LocalDateTime getLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(date, formatter);
    }
}
