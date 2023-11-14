package edu.hw5;

import edu.hw5.Task3.Task3;
import edu.hw5.Task3.parser.AgoParser;
import edu.hw5.Task3.parser.FormatParser;
import edu.hw5.Task3.parser.WordParser;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task3Test {
    static Arguments[] trueArguments() {
        return new Arguments[] {
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("2020-2-10", LocalDate.of(2020, 2, 10)),
            Arguments.of("2020-2-2", LocalDate.of(2020, 2, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("11/3/1976", LocalDate.of(1976, 3, 11)),
            Arguments.of("1/12/1976", LocalDate.of(1976, 12, 1)),
            Arguments.of("11/12/1976", LocalDate.of(1976, 12, 11)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of("11/3/20", LocalDate.of(2020, 3, 11)),
            Arguments.of("1/12/20", LocalDate.of(2020, 12, 1)),
            Arguments.of("11/12/20", LocalDate.of(2020, 12, 11)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().minusDays(1)),
            Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of("2234 days ago", LocalDate.now().minusDays(2234)),
            Arguments.of("3 weeks ago", LocalDate.now().minusWeeks(3)),
            Arguments.of("1 year ago", LocalDate.now().minusYears(1)),
            Arguments.of("10 months ago", LocalDate.now().minusMonths(10))
        };
    }

    static Arguments[] falseArguments() {
        return new Arguments[] {
            Arguments.of("11111"),
            Arguments.of(""),
            Arguments.of(" "),
            Arguments.of("2020-13-10"),
            Arguments.of("2020-12-32"),
            Arguments.of("-2020-12-12"),
            Arguments.of("2 week ago"),
            Arguments.of("1 years ago"),
            Arguments.of("-1 year ago"),
            Arguments.of("-1 years ago"),
            Arguments.of("1 year das"),
            Arguments.of("1 das ago"),
            Arguments.of("11/13/20"),
            Arguments.of("32/12/20"),
            Arguments.of("2020/3/3"),
            Arguments.of("3-3-2020"),
            Arguments.of("das years ago")
        };
    }

    @ParameterizedTest
    @MethodSource("trueArguments")
    void properInput(String input, LocalDate expected) {
        assertThat(Task3.parseDate(input)).isEqualTo(Optional.of(expected));
    }

    @ParameterizedTest
    @MethodSource("falseArguments")
    void wrongInput(String input) {
        assertThat(Task3.parseDate(input)).isEqualTo(Optional.empty());
    }

    @Test
    void nullInput() {
        assertThatThrownBy(() -> Task3.parseDate(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    class ParserTest {
        @Test
        void nullInput() {
            assertThatThrownBy(
                () -> new AgoParser(null).parse(null))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(
                () -> new FormatParser(null).parse(null))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(
                () -> new WordParser(null).parse(null))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
