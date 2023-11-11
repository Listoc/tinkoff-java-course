package edu.hw5.Task3;

import edu.hw5.Task3.parser.AgoParser;
import edu.hw5.Task3.parser.FormatParser;
import edu.hw5.Task3.parser.WordParser;
import java.time.LocalDate;
import java.util.Optional;

public class Task3 {
    public static Optional<LocalDate> parseDate(String string) {
        var parser = new WordParser(new AgoParser(new FormatParser(null)));
        var result = parser.parse(string);
        if (result == null) {
            return Optional.empty();
        }

        return Optional.of(result);
    }

    private Task3() {}
}
