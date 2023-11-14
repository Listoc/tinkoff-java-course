package edu.hw5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task2Test {
    @Nested
    class AllFridaysTest {
        @Test
        void properInput1() {
            var expected = List.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13),
                LocalDate.of(1925, 11, 13)
            );

            assertThat(Task2.findAllFridaysThirteenthByYear(1925)).isEqualTo(expected);
        }

        @Test
        void properInput2() {
            var expected = List.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)
            );

            assertThat(Task2.findAllFridaysThirteenthByYear(2024)).isEqualTo(expected);
        }

        @Test
        void tooLowInput() {
            assertThat(Task2.findAllFridaysThirteenthByYear(Integer.MIN_VALUE)).isEqualTo(null);
        }

        @Test
        void tooMuchInput() {
            assertThat(Task2.findAllFridaysThirteenthByYear(Integer.MAX_VALUE)).isEqualTo(null);
        }
    }

    @Nested
    class NextFridayTest {
        @Test
        void properInput1() {
            var expected = LocalDate.of(1925, 11, 13);

            assertThat(Task2.findNextFridayThirteenth(LocalDate.of(1925, 4, 10))).isEqualTo(expected);
        }

        @Test
        void properInput2() {
            var expected = LocalDate.of(2024, 9, 13);

            assertThat(Task2.findNextFridayThirteenth(LocalDate.of(2024, 4, 10))).isEqualTo(expected);
        }

        @Test
        void properInput3() {
            var expected = LocalDate.of(1925, 2, 13);

            assertThat(Task2.findNextFridayThirteenth(LocalDate.of(1924, 12, 14))).isEqualTo(expected);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task2.findNextFridayThirteenth(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
