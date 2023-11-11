package edu.hw5.Task3.parser;

import java.time.LocalDate;

public abstract class AbstractParser implements Parser {
    private final Parser nextParser;

    public AbstractParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract LocalDate parse(String string);

    final public LocalDate callNextParser(String string) {
        if (nextParser == null) {
            return null;
        }

        return nextParser.parse(string);
    }
}
