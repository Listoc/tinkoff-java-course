package edu.project3.reader;

import edu.project3.model.Log;
import edu.project3.parser.LogParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LogFileReader extends AbstractLogReader implements LogReader {

    public LogFileReader(LogParser parser) {
        super(parser);
    }

    public Stream<Log> readLogs(String string) throws IOException {
        if (string == null) {
            throw new IllegalArgumentException();
        }

        Stream<String> result = Stream.of();

        try (var entries = Files.newDirectoryStream(Path.of(""), string)) {
            for (var el : entries) {
                result = Stream.concat(result, Files.lines(el));
            }
        } catch (IOException e) {
            throw new IOException("Can't read from file");
        }

        return parse(result);
    }
}
