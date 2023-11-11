package edu.hw5.Task3.parser;

import java.time.LocalDate;

public class WordParser extends AbstractParser implements Parser {
    public WordParser(Parser nextParser) {
        super(nextParser);
    }

    public LocalDate parse(String string) {
        LocalDate result = null;

        if (string.equals("tomorrow")) {
            result = LocalDate.now().plusDays(1);
        }
        if (string.equals("yesterday")) {
            result = LocalDate.now().minusDays(1);
        }
        if (string.equals("today")) {
            result = LocalDate.now();
        }

        return result == null ? callNextParser(string) : result;
    }
}
