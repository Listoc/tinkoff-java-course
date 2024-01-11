package edu.project3;

import edu.project3.analyzer.LogAnalyzer;
import edu.project3.analyzer.MyLogAnalyzer;
import edu.project3.parser.LogParser;
import edu.project3.parser.MyLogParser;
import edu.project3.reader.LogFileReader;
import edu.project3.reader.LogReader;
import edu.project3.reader.LogUrlReader;
import edu.project3.writer.ADOCWriter;
import edu.project3.writer.MarkdownWriter;
import edu.project3.writer.ReportWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@SuppressWarnings({"ModifiedControlVariable", "MultipleStringLiterals"})
public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || args.length % 2 == 1) {
            throw new IllegalArgumentException("Wrong arguments count");
        }

        String path = "";
        ReportWriter writer = new MarkdownWriter();
        LogReader reader;
        LogAnalyzer analyzer = new MyLogAnalyzer();
        LogParser parser = new MyLogParser();
        LocalDate from = null;
        LocalDate to = null;

        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "--path":
                    i++;
                    path = args[i];
                    break;

                case "--format":
                    i++;
                    if (!args[i].equals("markdown") && !args[i].equals("adoc")) {
                        throw new IllegalArgumentException();
                    }

                    writer = args[i].equals("markdown") ? new MarkdownWriter() : new ADOCWriter();
                    break;

                case "--from":
                    try {
                        ++i;
                        from = LocalDate.parse(args[i], DateTimeFormatter.ISO_LOCAL_DATE);
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Wrong date format!");
                    }
                    break;

                case "--to":
                    try {
                        ++i;
                        to = LocalDate.parse(args[i], DateTimeFormatter.ISO_LOCAL_DATE);
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Wrong date format!");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Wrong Arguments! " + args[i]);
            }
        }

        Runner runner;

        if (path.startsWith("http")) {
            reader = new LogUrlReader(parser);
        } else {
            reader = new LogFileReader(parser);
        }

        runner = new Runner(path, reader, writer, analyzer, from, to);
        runner.run();
    }

    private Main() {}
}
