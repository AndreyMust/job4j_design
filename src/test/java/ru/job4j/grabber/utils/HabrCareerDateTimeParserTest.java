package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    @Test
    public void whenParseDateTimeOne() {
        DateTimeParser dateTimeParser = new HabrCareerDateTimeParser();
        LocalDateTime parsed = dateTimeParser.parse("2023-01-10T15:13:38+03:00");
        assertThat(parsed).isEqualTo("2023-01-10T15:13:38");
    }

    @Test
    public void whenParseDateTimeTwo() {
        DateTimeParser dateTimeParser = new HabrCareerDateTimeParser();
        LocalDateTime parsed = dateTimeParser.parse("2023-01-05T11:33:38+03:00");
        assertThat(parsed).isEqualTo("2023-01-05T11:33:38");
    }

    @Test
    public void whenParseDateTimeThree() {
        DateTimeParser dateTimeParser = new HabrCareerDateTimeParser();
        LocalDateTime parsed = dateTimeParser.parse("2023-01-05T11:33:38+03:00");
        assertThat(parsed).isBefore("2024-01-01T00:00:01")
                .isAfter("2020-01-01T00:30:00");
    }
}