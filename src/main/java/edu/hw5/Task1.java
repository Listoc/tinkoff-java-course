package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

public class Task1 {
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MINUTES_IN_DAY = MINUTES_IN_HOUR * 24;

    public static Duration getAverageTime(String timeString) {
        if (timeString == null) {
            throw new IllegalArgumentException("Null input!");
        }

        if (timeString.isEmpty() || timeString.equals(" ")) {
            return null;
        }

        int avg = timeString
            .lines()
            .collect(Collectors.averagingInt(Task1::mapStringToMinutes))
            .intValue();

        return Duration.ofMinutes(avg);
    }

    private static int mapStringToMinutes(String string) {
        var split = string.split(" - ");
        if (split.length != 2) {
            throw new IllegalArgumentException("Wrong format!");
        }

        LocalDateTime firstDate;
        LocalDateTime secondDate;

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        try {
            firstDate = LocalDateTime.parse(split[0], formatter);
            secondDate = LocalDateTime.parse(split[1], formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Wrong date format!");
        }

        if (!isDatesCorrect(firstDate, secondDate)) {
            throw new IllegalArgumentException("Wrong dates order");
        }

        int days = secondDate.getDayOfMonth() - firstDate.getDayOfMonth();
        int hours = secondDate.getHour() - firstDate.getHour();
        int minutes = secondDate.getMinute() - firstDate.getMinute();

        return days * MINUTES_IN_DAY + hours * MINUTES_IN_HOUR + minutes;
    }

    private static boolean isDatesCorrect(LocalDateTime first, LocalDateTime second) {
        if (second.getYear() < first.getYear()
            || second.getDayOfYear() < first.getDayOfYear()
            || (second.getHour() < first.getHour() && second.getDayOfMonth() == first.getDayOfMonth())
            || (second.getMinute() < first.getMinute() && second.getHour() == first.getHour())) {
            return false;
        }

        return true;
    }

    private Task1() {}
}
