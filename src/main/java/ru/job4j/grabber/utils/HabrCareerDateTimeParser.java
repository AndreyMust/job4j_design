package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HabrCareerDateTimeParser implements DateTimeParser {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(parse, DATE_TIME_FORMAT);
    }

    public static void main(String[] args) {
        DateTimeParser dateTimeParser = new HabrCareerDateTimeParser();
        LocalDateTime asserted =  LocalDateTime.parse("2023-01-10T15:13:38", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(dateTimeParser.parse("2023-01-10T15:13:38+03:00"));
        System.out.println(asserted);

    }
}