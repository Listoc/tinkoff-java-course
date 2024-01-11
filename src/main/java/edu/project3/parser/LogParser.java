package edu.project3.parser;

import edu.project3.model.Log;
import java.util.stream.Stream;

public interface LogParser {
    Stream<Log> parse(Stream<String> lines);
}
