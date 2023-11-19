package edu.project3.reader;

import edu.project3.model.Log;
import edu.project3.parser.LogParser;
import java.util.stream.Stream;

public abstract class AbstractLogReader implements LogReader {
    private final LogParser parser;

    protected AbstractLogReader(LogParser parser) {
        this.parser = parser;
    }

    protected Stream<Log> parse(Stream<String> lines) {
        return parser.parse(lines);
    }
}
