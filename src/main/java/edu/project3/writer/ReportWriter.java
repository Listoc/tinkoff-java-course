package edu.project3.writer;

import edu.project3.model.LogReport;
import java.io.IOException;

public interface ReportWriter {
    void write(LogReport report) throws IOException;
}
