package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task4Test {
    static Arguments[] trueArguments() {
        return new Arguments[] {
            Arguments.of("test!test"),
            Arguments.of("$"),
            Arguments.of("$test"),
            Arguments.of("test$"),
            Arguments.of("|test|"),
            Arguments.of("Pa$$w0rd"),
            Arguments.of("$@$@!%^")
        };
    }

    static Arguments[] falseArguments() {
        return new Arguments[] {
            Arguments.of("testtest"),
            Arguments.of("g"),
            Arguments.of("1234"),
            Arguments.of("goodpassword"),
            Arguments.of("dollardollar"),
        };
    }

    @ParameterizedTest
    @MethodSource("trueArguments")
    void trueInput(String input) {
        assertThat(Task4.checkPassword(input)).isEqualTo(true);
    }

    @ParameterizedTest
    @MethodSource("falseArguments")
    void falseInput(String input) {
        assertThat(Task4.checkPassword(input)).isEqualTo(false);
    }

    @Test
    void nullInput() {
        assertThatThrownBy(() -> Task4.checkPassword(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
