package com.jimboyz.cims.model;

import com.jimboyz.cims.AppProperties;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    static AppProperties appProperties = new AppProperties();

    public static boolean isTodayWithinRange(String startDateStr, String endDateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(appProperties.dateFormatString);
            LocalDate today = LocalDate.now();
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);

            return (!today.isBefore(startDate)) &&  (!today.isAfter(endDate));

        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format. Format must be: " + appProperties.dateFormatString);
        }
    }
}
