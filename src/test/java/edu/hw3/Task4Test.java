package edu.hw3;

import edu.hw3.task4.Task4;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    static Arguments[] properInput() {
        return new Arguments[] {
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(49, "XLIX"),
            Arguments.of(4999, "MMMMCMXCIX"),
            Arguments.of(501, "DI"),
            Arguments.of(1, "I"),
            Arguments.of(2000, "MM"),
            Arguments.of(111, "CXI"),
        };
    }

    @ParameterizedTest
    @MethodSource("properInput")
    void properInput(int input, String expected) {
        assertThat(Task4.convertToRoman(input)).isEqualTo(expected);
    }

    @Test
    void negativeInput() {
        assertThat(Task4.convertToRoman(-4)).isEqualTo(null);
    }

    @Test
    void greaterInput() {
        assertThat(Task4.convertToRoman(5000)).isEqualTo(null);
    }
}
