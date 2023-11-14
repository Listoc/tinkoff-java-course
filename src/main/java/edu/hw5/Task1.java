package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Task1 {
    public static Duration getAverageTime(String timeString) {
        if (timeString == null) {
            throw new IllegalArgumentException("Null input!");
        }

        if (timeString.isEmpty() || timeString.equals(" ")) {
            return null;
        }

        var sum = timeString
            .lines()
            .map(Task1::mapStringToDuration)
            .reduce(Duration::plus);

        return sum.orElseThrow().dividedBy(timeString.lines().count()).truncatedTo(ChronoUnit.MINUTES);
    }

    private static Duration mapStringToDuration(String string) {
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

        return Duration.between(firstDate, secondDate);
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
