package edu.hw7;

import edu.hw7.task2.ParallelFactorial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task2Test {
    static Arguments[] properArguments() {
        return new Arguments[] {
            Arguments.of(0, 1L),
            Arguments.of(1, 1L),
            Arguments.of(2, 2L),
            Arguments.of(3, 6L),
            Arguments.of(4, 24L),
            Arguments.of(5, 120L),
            Arguments.of(6, 720L),
            Arguments.of(15, 1_307_674_368_000L)
        };
    }

    @ParameterizedTest
    @MethodSource("properArguments")
    void properInput(int number, long expected) {
        assertThat(ParallelFactorial.getFactorial(number)).isEqualTo(expected);
    }

    @Test
    void wrongInput() {
        assertThatThrownBy(() -> ParallelFactorial.getFactorial(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
