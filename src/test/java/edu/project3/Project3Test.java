package edu.project3;

import edu.project3.analyzer.MyLogAnalyzer;
import edu.project3.model.Log;
import edu.project3.model.LogReport;
import edu.project3.model.RequestMethod;
import edu.project3.parser.MyLogParser;
import edu.project3.reader.LogFileReader;
import edu.project3.writer.ADOCWriter;
import edu.project3.writer.MarkdownWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Project3Test {
    @AfterAll
    @BeforeAll
    static void clear() throws IOException {
        Files.deleteIfExists(Path.of("logsTest.txt"));
        Files.deleteIfExists(Path.of("logReport.md"));
        Files.deleteIfExists(Path.of("logReport.adoc"));
    }

    @Nested
    class ParserTest {
        @Test
        void properIPv4Input() {
            String log =
                "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
            var expected = new Log(
                "93.180.71.3",
                " ",
                OffsetDateTime.of(2015, 5, 17, 8, 5, 32, 0, ZoneOffset.ofHours(0)),
                RequestMethod.GET,
                "/downloads/product_1",
                304,
                0
            );

            assertThat(new MyLogParser().parse(Stream.of(log)).findFirst().get()).isEqualTo(expected);
        }

        @Test
        void properIPv6Input() {
            String log =
                "2001:0db8:0000:0000:0000:ff00:0042:8329 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
            var expected = new Log(
                "2001:0db8:0000:0000:0000:ff00:0042:8329",
                " ",
                OffsetDateTime.of(2015, 5, 17, 8, 5, 32, 0, ZoneOffset.ofHours(0)),
                RequestMethod.GET,
                "/downloads/product_1",
                304,
                0
            );

            assertThat(new MyLogParser().parse(Stream.of(log)).findFirst().get()).isEqualTo(expected);
        }

        static Arguments[] wrongInputs() {
            return new Arguments[] {
                //Bad IP
                Arguments.of(
                    "93.4180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),

                Arguments.of(
                    "180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),

                //Bad Date
                Arguments.of(
                    "93.180.71.3 - - [33/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),

                Arguments.of(
                    "93.180.71.3 - - [17/May/2015:25:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),

                //Bad Method
                Arguments.of(
                    "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"BadMethod /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),

                //Wrong format
                Arguments.of(
                    "93.180.71.3 - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),

                Arguments.of(
                    "93.180.71.3 - - 17/May/2015:08:05:32 +0000 \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),

                Arguments.of(
                    "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\""
                ),

                Arguments.of(
                    ""
                ),
            };
        }

        @ParameterizedTest
        @MethodSource("wrongInputs")
        void wrongInput(String log) {
            assertThatThrownBy(() -> new MyLogParser().parse(Stream.of(log)).toList()).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> new MyLogParser().parse(null).toList()).isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    class AnalyzerTest {
        @Test
        void properInput() {
            String logs = """
            93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
            217.168.17.5 - - [17/May/2015:08:05:34 +0000] "GET /downloads/product_1 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
            """;

            var generalInfo = new HashMap<String, String>();
            generalInfo.put("Последняя дата", "2015-05-17T08:05:34Z");
            generalInfo.put("Первая дата", "2015-05-17T08:05:23Z");
            generalInfo.put("Средний размер ответа", "122b");
            generalInfo.put("Файл", "test1.txt");
            generalInfo.put("Количество запросов", "4");

            var responseCodes = new HashMap<Integer, Long>();
            responseCodes.put(304, 3L);
            responseCodes.put(200, 1L);

            var requestedResources = new HashMap<String, Long>();
            requestedResources.put("/downloads/product_1", 4L);

            var methods = new HashMap<RequestMethod, Long>();
            methods.put(RequestMethod.GET, 4L);

            var ip = new HashMap<String, Long>();
            ip.put("93.180.71.3", 2L);
            ip.put("80.91.33.133", 1L);
            ip.put("217.168.17.5", 1L);



            var logsStream = new MyLogParser().parse(logs.lines());
            var resultReport = new MyLogAnalyzer().getReport(logsStream, "test1.txt");
            var expectedReport = new LogReport(
                generalInfo,
                responseCodes,
                requestedResources,
                methods,
                ip
            );

            assertThat(resultReport).isEqualTo(expectedReport);
        }

        @Test
        void properWithTimeInput() {
            String logs = """
                94.75.199.161 - - [30/May/2014:21:05:41 +0000] "GET /downloads/product_2 HTTP/1.1" 404 338 "-" "Debian APT-HTTP/1.3 (0.9.7.9)"
                54.77.181.41 - - [30/May/2014:21:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 404 331 "-" "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
                93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
                217.168.17.5 - - [17/May/2015:08:05:34 +0000] "GET /downloads/product_1 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
                """;

            var generalInfo = new HashMap<String, String>();
            generalInfo.put("Последняя дата", "2015-05-17T08:05:34Z");
            generalInfo.put("Первая дата", "2015-05-17T08:05:23Z");
            generalInfo.put("Средний размер ответа", "122b");
            generalInfo.put("Файл", "test1.txt");
            generalInfo.put("Количество запросов", "4");

            var responseCodes = new HashMap<Integer, Long>();
            responseCodes.put(304, 3L);
            responseCodes.put(200, 1L);

            var requestedResources = new HashMap<String, Long>();
            requestedResources.put("/downloads/product_1", 4L);

            var methods = new HashMap<RequestMethod, Long>();
            methods.put(RequestMethod.GET, 4L);

            var ip = new HashMap<String, Long>();
            ip.put("93.180.71.3", 2L);
            ip.put("80.91.33.133", 1L);
            ip.put("217.168.17.5", 1L);



            var logsStream = new MyLogParser().parse(logs.lines());
            var resultReport = new MyLogAnalyzer().getReport(logsStream, "test1.txt", LocalDate.of(2015, 1, 1));
            var expectedReport = new LogReport(
                generalInfo,
                responseCodes,
                requestedResources,
                methods,
                ip
            );

            assertThat(resultReport).isEqualTo(expectedReport);
        }
    }

    @Nested
    class ReaderTest {
        @Test
        void properInput() throws IOException {
            String logs = """
                93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
                """;

            Files.writeString(Path.of("logsTest.txt"), logs);

            var reader = new LogFileReader(new MyLogParser());

            var result = reader.readLogs("logsTest.txt");

            var expected1 = new Log(
                "93.180.71.3",
                " ",
                OffsetDateTime.of(2015, 5, 17, 8, 5, 32, 0, ZoneOffset.ofHours(0)),
                RequestMethod.GET,
                "/downloads/product_1",
                304,
                0
            );

            var expected2 = new Log(
                "80.91.33.133",
                " ",
                OffsetDateTime.of(2015, 5, 17, 8, 5, 24, 0, ZoneOffset.ofHours(0)),
                RequestMethod.GET,
                "/downloads/product_1",
                304,
                0
            );

            var expected = List.of(expected1, expected2);

            assertThat(result.toList()).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Nested
    class RunnerTest {
        @Test
        void markdownWriter() throws IOException {
            String logs = """
                93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
                """;

            Files.writeString(Path.of("logsTest.txt"), logs);

            var runner = new Runner("logsTest.txt", new LogFileReader(new MyLogParser()), new MarkdownWriter(), new MyLogAnalyzer(), null, null);
            runner.run();

            assertThat(Files.exists(Path.of("logReport.md"))).isTrue();
        }

        @Test
        void adocWriter() throws IOException {
            String logs = """
                93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
                """;

            Files.writeString(Path.of("logsTest.txt"), logs);

            var runner = new Runner("logsTest.txt", new LogFileReader(new MyLogParser()), new ADOCWriter(), new MyLogAnalyzer(), null, null);
            runner.run();

            assertThat(Files.exists(Path.of("logReport.adoc"))).isTrue();
        }
    }
}
