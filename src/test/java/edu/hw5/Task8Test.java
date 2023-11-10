package edu.hw5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task8Test {
    @Nested
    class FirstRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of("1"),
                Arguments.of("0"),
                Arguments.of("010"),
                Arguments.of("011"),
                Arguments.of("01010"),
                Arguments.of("11111"),
                Arguments.of("111111111100001100110")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("00"),
                Arguments.of("11"),
                Arguments.of("01"),
                Arguments.of("10"),
                Arguments.of("0001"),
                Arguments.of(""),
                Arguments.of("000001"),
                Arguments.of("00110011001100001111010101")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task8.checkFirstRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task8.checkFirstRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task8.checkFirstRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class SecondRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of("001"),
                Arguments.of("011"),
                Arguments.of("000"),
                Arguments.of("0101011"),
                Arguments.of("011111111100001100110"),
                Arguments.of("1001"),
                Arguments.of("1011"),
                Arguments.of("1000"),
                Arguments.of("10101011"),
                Arguments.of("11010101000111"),
                Arguments.of("1011111111100001100110")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("101"),
                Arguments.of("111"),
                Arguments.of("100"),
                Arguments.of("1101011"),
                Arguments.of("1010101000111"),
                Arguments.of("111111111100001100110"),
                Arguments.of("0001"),
                Arguments.of("0011"),
                Arguments.of("0000"),
                Arguments.of("00101011"),
                Arguments.of("01010101000111"),
                Arguments.of("0011111111100001100110")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task8.checkSecondRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task8.checkSecondRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task8.checkSecondRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class ThirdRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of(""),
                Arguments.of("1"),
                Arguments.of("11"),
                Arguments.of("111"),
                Arguments.of("11111111111111111111"),
                Arguments.of("1010101"),
                Arguments.of("011101111001011110")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("0"),
                Arguments.of("00"),
                Arguments.of("0000"),
                Arguments.of("1001"),
                Arguments.of("011001010"),
                Arguments.of("01110111100101111")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task8.checkThirdRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task8.checkThirdRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task8.checkThirdRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class FourthRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of(""),
                Arguments.of("1"),
                Arguments.of("1111"),
                Arguments.of("11111"),
                Arguments.of("11111111111111111111"),
                Arguments.of("1010101"),
                Arguments.of("011101111001011110"),
                Arguments.of("110"),
                Arguments.of("1110"),
                Arguments.of("011"),
                Arguments.of("101"),
                Arguments.of("01110")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("11"),
                Arguments.of("111")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task8.checkFourthRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task8.checkFourthRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task8.checkFourthRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class FifthRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of(""),
                Arguments.of("1"),
                Arguments.of("1111"),
                Arguments.of("11111"),
                Arguments.of("11111111111111111111"),
                Arguments.of("1010101"),
                Arguments.of("111010111010"),
                Arguments.of("101"),
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("01"),
                Arguments.of("100"),
                Arguments.of("0"),
                Arguments.of("10101010100")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task8.checkFifthRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task8.checkFifthRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task8.checkFifthRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class SixthRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of("00"),
                Arguments.of("100"),
                Arguments.of("010"),
                Arguments.of("001"),
                Arguments.of("000001000000000"),
                Arguments.of("000000000000000000"),
                Arguments.of("100000000000000"),
                Arguments.of("000000000000001"),
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("01"),
                Arguments.of("10"),
                Arguments.of("0110"),
                Arguments.of("11"),
                Arguments.of("111111111"),
                Arguments.of("10001000"),
                Arguments.of("1000001"),
                Arguments.of("0001000001")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task8.checkSixthRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task8.checkSixthRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task8.checkSixthRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class SeventhRegexTest {
        static Arguments[] trueArguments() {
            return new Arguments[] {
                Arguments.of("00"),
                Arguments.of("100"),
                Arguments.of("010"),
                Arguments.of("001"),
                Arguments.of("000001000000000"),
                Arguments.of("000000000000000000"),
                Arguments.of("100000000000000"),
                Arguments.of("000000000000001"),
                Arguments.of("010101010101"),
                Arguments.of("010001010000010")
            };
        }

        static Arguments[] falseArguments() {
            return new Arguments[] {
                Arguments.of("11"),
                Arguments.of("1011"),
                Arguments.of("0110"),
                Arguments.of("111111111"),
                Arguments.of("110001000"),
                Arguments.of("10000011"),
                Arguments.of("100011000001")
            };
        }

        @ParameterizedTest
        @MethodSource("trueArguments")
        void trueInput(String input) {
            assertThat(Task8.checkSeventhRegex(input)).isEqualTo(true);
        }

        @ParameterizedTest
        @MethodSource("falseArguments")
        void falseInput(String input) {
            assertThat(Task8.checkSeventhRegex(input)).isEqualTo(false);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> Task8.checkSeventhRegex(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
