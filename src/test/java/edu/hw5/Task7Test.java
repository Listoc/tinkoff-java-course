package edu.hw5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task7Test {

    @Nested
    class FirstRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of("110"),
                Arguments.of("100"),
                Arguments.of("000"),
                Arguments.of("010"),
                Arguments.of("1100011"),
                Arguments.of("0001101")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("00"),
                Arguments.of("11"),
                Arguments.of("01"),
                Arguments.of("10"),
                Arguments.of("001"),
                Arguments.of("1110"),
                Arguments.of("210")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task7.checkFirstRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task7.checkFirstRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task7.checkFirstRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class SecondRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of("00"),
                Arguments.of("11"),
                Arguments.of("010"),
                Arguments.of("101"),
                Arguments.of("1010101000111"),
                Arguments.of("0100101010"),
                Arguments.of("0"),
                Arguments.of("1")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("01"),
                Arguments.of("10"),
                Arguments.of("101100"),
                Arguments.of("0110101"),
                Arguments.of("1201")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task7.checkSecondRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task7.checkSecondRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task7.checkSecondRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class ThirdRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of("1"),
                Arguments.of("0"),
                Arguments.of("01"),
                Arguments.of("10"),
                Arguments.of("000"),
                Arguments.of("111"),
                Arguments.of("101")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of(""),
                Arguments.of("1111"),
                Arguments.of("0000"),
                Arguments.of("1010"),
                Arguments.of("101010010010101"),
                Arguments.of("0110")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task7.checkThirdRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task7.checkThirdRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task7.checkThirdRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
