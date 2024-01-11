package edu.project3.reader;

import edu.project3.model.Log;
import edu.project3.parser.LogParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class LogUrlReader extends AbstractLogReader implements LogReader {

    public LogUrlReader(LogParser parser) {
        super(parser);
    }

    @Override
    public Stream<Log> readLogs(String string) throws IOException {
        if (string == null) {
            throw new IllegalArgumentException();
        }

        var request = HttpRequest.newBuilder(URI.create(string)).GET().build();
        try (var client = HttpClient.newHttpClient()) {
            return parse(client.send(request, HttpResponse.BodyHandlers.ofString()).body().lines());
        } catch (InterruptedException | IOException e) {
            throw new IOException("Can't read from source");
        }
    }
}
