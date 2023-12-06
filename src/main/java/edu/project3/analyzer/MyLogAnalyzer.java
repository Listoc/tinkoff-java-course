package edu.project3.analyzer;

import edu.project3.model.Log;
import edu.project3.model.LogReport;
import edu.project3.model.RequestMethod;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyLogAnalyzer implements LogAnalyzer {
    private static final int LOGS_COUNT = 3;

    public LogReport getReport(Stream<Log> logs, String path, LocalDate from, LocalDate to) {
        OffsetDateTime finalFrom;
        OffsetDateTime finalTo;

        if (from == null) {
            finalFrom = OffsetDateTime.MIN;
        } else {
            finalFrom = OffsetDateTime.of(from, LocalTime.MIN, ZoneOffset.ofTotalSeconds(0));
        }

        if (to == null) {
            finalTo = OffsetDateTime.MAX;
        } else {
            finalTo = OffsetDateTime.of(to, LocalTime.MIN, ZoneOffset.ofTotalSeconds(0));
        }

        var list = logs
            .filter((log) -> log.time().isAfter(finalFrom) && log.time().isBefore(finalTo))
            .toList();

        if (list.isEmpty()) {
            throw new IllegalArgumentException("No logs in file!");
        }

        return new LogReport(
            getGeneralInfo(list, path),
            getMostUsedResponseCodes(list),
            getMostRequestedResources(list),
            getMostUsedRequestMethods(list),
            getIPsWithMostRequests(list)
        );
    }

    @Override
    public LogReport getReport(Stream<Log> logs, String path, LocalDate from) {
        return getReport(logs, path, from, LocalDate.MAX);
    }

    @Override
    public LogReport getReport(Stream<Log> logs, String path) {
        return getReport(logs, path, LocalDate.MIN, LocalDate.MAX);
    }

    private static Map<String, String> getGeneralInfo(List<Log> list, String path) {
        var map = new HashMap<String, String>();

        map.put("Файл", path);
        map.put(
            "Первая дата",
            list.stream()
                .min(Comparator.comparing(Log::time))
                .orElseThrow()
                .time()
                .toString()
        );

        map.put(
            "Последняя дата",
            list.stream()
                .max(Comparator.comparing(Log::time))
                .orElseThrow()
                .time().toString()
        );
        map.put("Количество запросов", String.valueOf(list.size()));
        map.put(
            "Средний размер ответа",
            list.stream()
                .collect(
                    Collectors.averagingInt(Log::bytesSent)
                )
                .intValue() + "b"
        );

        return map;
    }

    private static Map<Integer, Long> getMostUsedResponseCodes(List<Log> list) {
        var map = list
            .stream()
            .collect(
                Collectors.groupingBy(Log::status, Collectors.counting())
            );

        return map.entrySet()
            .stream()
            .sorted((entry1, entry2) -> (int) (entry2.getValue() - entry1.getValue()))
            .limit(LOGS_COUNT)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map<String, Long> getMostRequestedResources(List<Log> list) {
        var map = list
            .stream()
            .collect(
                Collectors.groupingBy(Log::resource, Collectors.counting())
            );

        return getMostRequested(map);
    }

    private static Map<RequestMethod, Long> getMostUsedRequestMethods(List<Log> list) {
        var map = list
            .stream()
            .collect(
                Collectors.groupingBy(Log::method, Collectors.counting())
            );

        return getMostRequested(map);
    }

    private static Map<String, Long> getIPsWithMostRequests(List<Log> list) {
        var map = list
            .stream()
            .collect(
                Collectors.groupingBy(Log::ip, Collectors.counting())
            );

        return getMostRequested(map);
    }

    private static <T> Map<T, Long> getMostRequested(Map<T, Long> map) {
        return map.entrySet()
            .stream()
            .sorted((entry1, entry2) -> (int) (entry2.getValue() - entry1.getValue()))
            .limit(LOGS_COUNT)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
