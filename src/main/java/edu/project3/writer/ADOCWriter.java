package edu.project3.writer;

import edu.project3.model.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import static edu.project3.model.LogReport.RESPONSE_CODE_NAMES;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class ADOCWriter implements ReportWriter {
    private static final Path PATH = Path.of("logReport.adoc");

    @Override
    public void write(LogReport report) throws IOException {
        String result = writeTwoColumnsTables(report.generalInfo(), "Общая информация", "Метрика", "Значение")
            + writeTwoColumnsTables(report.requestedResources(), "Запрашиваемые ресурсы", "Ресурс", "Количество")
            + writeTwoColumnsTables(report.methods(), "Методы", "Метод", "Количество")
            + writeTwoColumnsTables(report.ip(), "IP", "IP", "Количество")
            + writeThreeColumnsTable(report.responseCodes(), "Коды ответа", "Код", "Имя", "Количество");


        try {
            Files.writeString(PATH, result);
        } catch (IOException e) {
            throw new IOException("Can't write to file");
        }
    }

    private <T, D> String writeTwoColumnsTables(Map<T, D> map, String name, String firstHeader, String secondHeader) {
        var builder = new StringBuilder();
        builder.append("#### ");
        builder.append(name);
        builder.append("\n\n");
        builder.append("[cols=\"1,1\"]\n|===\n");
        String format = "|%-30s|%-30s\n";
        builder.append(String.format(format, firstHeader, secondHeader));

        for (var key : map.keySet()) {
            builder.append(String.format(
                format,
                key,
                map.get(key)
            ));
        }

        builder.append("|===\n");

        return builder.toString();
    }

    private String writeThreeColumnsTable(
        Map<Integer, Long> map,
        String name,
        String firstColumn,
        String secondColumn,
        String thirdColumn) {
        var builder = new StringBuilder();

        String format = "|%-32s|%-32s|%-32s\n";
        builder.append("#### ");
        builder.append(name);
        builder.append("\n\n");
        builder.append("[cols=\"1,1,1\"]\n|===\n");

        builder.append(String.format(format, firstColumn, secondColumn, thirdColumn));

        for (var key : map.keySet()) {
            builder.append(String.format(
                format,
                key,
                RESPONSE_CODE_NAMES.get(key),
                map.get(key)
            ));
        }

        builder.append("|===\n");

        return builder.toString();
    }
}
