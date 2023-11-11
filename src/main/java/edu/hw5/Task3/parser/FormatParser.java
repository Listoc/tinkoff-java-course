package edu.hw5.Task3.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class FormatParser extends AbstractParser implements Parser {
    public FormatParser(Parser nextParser) {
        super(nextParser);
    }

    public LocalDate parse(String string) {
        var formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var formatter2 = DateTimeFormatter.ofPattern("yyyy-M-dd");
        var formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-d");
        var formatter4 = DateTimeFormatter.ofPattern("yyyy-M-d");
        var formatter5 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var formatter6 = DateTimeFormatter.ofPattern("dd/MM/yy");
        var formatter7 = DateTimeFormatter.ofPattern("d/MM/yyyy");
        var formatter8 = DateTimeFormatter.ofPattern("d/MM/yy");
        var formatter9 = DateTimeFormatter.ofPattern("dd/M/yyyy");
        var formatter10 = DateTimeFormatter.ofPattern("dd/M/yy");
        var formatter11 = DateTimeFormatter.ofPattern("d/M/yyyy");
        var formatter12 = DateTimeFormatter.ofPattern("d/M/yy");

        var list = List.of(
            formatter1,
            formatter2,
            formatter3,
            formatter4,
            formatter5,
            formatter6,
            formatter7,
            formatter8,
            formatter9,
            formatter10,
            formatter11,
            formatter12);

        for (var formatter : list) {
            try {
                return LocalDate.parse(string, formatter);
            } catch (DateTimeParseException ignored) { }
        }

        return callNextParser(string);
    }
}
