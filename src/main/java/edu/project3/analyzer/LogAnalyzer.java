package edu.project3.analyzer;

import edu.project3.model.Log;
import edu.project3.model.LogReport;
import java.time.LocalDate;
import java.util.stream.Stream;

public interface LogAnalyzer {
    LogReport getReport(Stream<Log> logs, String path, LocalDate from, LocalDate to);

    LogReport getReport(Stream<Log> logs, String path, LocalDate from);

    LogReport getReport(Stream<Log> logs, String path);
}
