package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task1Test {
    static Arguments[] properInputs() {
        return new Arguments[] {
            Arguments.of(
            """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                """,
                3 * 60 + 40),
            Arguments.of(
                """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                2022-04-02, 21:30 - 2022-04-03, 00:00
                """,
                3 * 60 + 16
            ),
            Arguments.of(
                """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                2022-04-02, 21:30 - 2022-04-05, 21:30
                """,
                1 * 24 * 60 + 2 * 60 + 26
            ),
            Arguments.of(
                """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                """,
                3 * 60 + 30
            ),
            Arguments.of(
                """
                2022-03-12, 20:20 - 2022-03-12, 20:20
                """,
                0
            )
        };
    }

    @ParameterizedTest
    @MethodSource("properInputs")
    void properInput(String timeStrings, int expectedMinutes) {
        assertThat(Task1.getAverageTime(timeStrings)).isEqualTo(Duration.ofMinutes(expectedMinutes));
    }

    @Test
    void nullInput() {
        assertThatThrownBy(() -> Task1.getAverageTime(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void emptyStringInput() {
        assertThat(Task1.getAverageTime("")).isEqualTo(null);
    }

    static Arguments[] wrongInputs() {
        return new Arguments[] {
            // Too many dates
            Arguments.of("2022-03-12, 20:20 - 2022-03-12, 23:50 - 2022-03-12, 23:59"),
            // Too few dates
            Arguments.of("2022-03-12, 20:20"),
            // Minutes above 59
            Arguments.of("2022-03-12, 25:61 - 2022-03-12, 23:61"),
            // Second date earlier than first
            Arguments.of("2030-03-12, 20:20 - 2022-03-12, 23:50"),
            //
            Arguments.of("dasdasdsa")
        };
    }

    @ParameterizedTest
    @MethodSource("wrongInputs")
    void wrongInput(String timeString) {
        assertThatThrownBy(() -> Task1.getAverageTime(timeString)).isInstanceOf(IllegalArgumentException.class);
    }
}
