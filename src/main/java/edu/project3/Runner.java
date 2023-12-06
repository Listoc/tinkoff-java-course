package edu.project3;

import edu.project3.analyzer.LogAnalyzer;
import edu.project3.reader.LogReader;
import edu.project3.writer.ReportWriter;
import java.io.IOException;
import java.time.LocalDate;

@SuppressWarnings("RegexpSinglelineJava")
public class Runner {
    private final String path;
    private final LogReader reader;
    private final ReportWriter writer;
    private final LogAnalyzer analyzer;
    private final LocalDate from;
    private final LocalDate to;

    public Runner(
        String path,
        LogReader reader,
        ReportWriter writer,
        LogAnalyzer analyzer,
        LocalDate from,
        LocalDate to
    ) {
        this.path = path;
        this.reader = reader;
        this.writer = writer;
        this.analyzer = analyzer;
        this.from = from;
        this.to = to;
    }

    public void run() {
        try {
            var logs = reader.readLogs(path);

            writer.write(analyzer.getReport(logs, path, from, to));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
