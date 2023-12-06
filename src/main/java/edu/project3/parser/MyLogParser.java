package edu.project3.parser;

import edu.project3.model.Log;
import edu.project3.model.RequestMethod;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SuppressWarnings({"LineLength", "MagicNumber"})
public class MyLogParser implements LogParser {
    private static final String REGEXP = "((\\d{1,3}\\.){3}\\d{1,3}|(\\w{0,4}:){1,7}\\w{0,4}) -(.*)- \\[(\\d{1,2}/[A-Z][a-z]{2}/\\d{4}:\\d{2}:\\d{2}:\\d{2} \\+\\d{4})] \"(.+) (.+) .+\" (\\d{3}) (\\d+) \".*\" \".*\"";
    private static final Pattern PATTERN = Pattern.compile(REGEXP);
    private static final String EXCEPTION_MESSAGE = "Wrong format!";

    @Override
    public Stream<Log> parse(Stream<String> lines) {
        return lines.map(MyLogParser::logMapper);
    }

    private static Log logMapper(String string) {
        var matcher = PATTERN.matcher(string);
        String ip;
        String name;
        String date;
        String method;
        String resource;
        String status;
        String bytesCount;

        if (matcher.matches()) {
            ip = matcher.group(1);
            name = matcher.group(4);
            date = matcher.group(5);
            method = matcher.group(6);
            resource = matcher.group(7);
            status = matcher.group(8);
            bytesCount = matcher.group(9);

            return new Log(
                ip,
                name,
                parseTime(date),
                RequestMethod.valueOf(method),
                resource,
                Integer.parseInt(status),
                Integer.parseInt(bytesCount)
            );
        }

        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
    }

    private static OffsetDateTime parseTime(String time) {
        try {
            var formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss xxxx", Locale.ENGLISH);
            return OffsetDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
