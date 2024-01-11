package edu.project3.reader;

import edu.project3.model.Log;
import java.io.IOException;
import java.util.stream.Stream;

public interface LogReader {
    Stream<Log> readLogs(String path) throws IOException;
}
