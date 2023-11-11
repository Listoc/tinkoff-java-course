package edu.hw5.Task3.parser;

import java.time.LocalDate;

public class AgoParser extends AbstractParser implements Parser {
    private static final int WORDS_COUNT = 3;

    public AgoParser(Parser nextParser) {
        super(nextParser);
    }

    public LocalDate parse(String string) {
        var split = string.split(" ");

        if (split.length != WORDS_COUNT) {
            return callNextParser(string);
        }

        int number;

        try {
            number = Integer.parseInt(split[0]);
        } catch (NumberFormatException e) {
            return callNextParser(string);
        }

        if (number <= 0 || !checkNumberAndEnding(number, split[1]) || !split[2].equals("ago")) {
            return callNextParser(string);
        }

        return switch (split[1]) {
            case "day", "days" -> parseDaysAgoDate(number);

            case "week", "weeks" -> parseWeeksAgoDate(number);

            case "month", "months" -> parseMonthsAgoDate(number);

            case "year", "years" -> parseYearsAgoDate(number);

            default -> callNextParser(string);
        };
    }

    private static LocalDate parseDaysAgoDate(int number) {
        return LocalDate.now().minusDays(number);
    }

    private static LocalDate parseWeeksAgoDate(int number) {
        return LocalDate.now().minusWeeks(number);
    }

    private static LocalDate parseMonthsAgoDate(int number) {
        return LocalDate.now().minusMonths(number);
    }

    private static LocalDate parseYearsAgoDate(int number) {
        return LocalDate.now().minusYears(number);
    }

    private static boolean checkNumberAndEnding(int number, String word) {
        if (number == 1 && word.endsWith("s")) {
            return false;
        }

        if (number != 1 && !word.endsWith("s")) {
            return false;
        }

        return true;
    }
}
