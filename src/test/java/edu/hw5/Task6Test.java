package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task6Test {
    static Arguments[] trueArguments() {
        return new Arguments[] {
            Arguments.of("abc", "achfdbaabgabcaabg"),
            Arguments.of("", "someString"),
            Arguments.of("s", "someString"),
            Arguments.of("ac", "abc"),
            Arguments.of("2468", "0123456789"),
            Arguments.of("", "")
        };
    }

    static Arguments[] falseArguments() {
        return new Arguments[] {
            Arguments.of("abc", "achfdbaabgabaabg"),
            Arguments.of("abc", "acab"),
            Arguments.of("2468", "01234567"),
            Arguments.of("a", "")
        };
    }

    @ParameterizedTest
    @MethodSource("trueArguments")
    void trueInput(String subSequence, String sequence) {
        assertThat(Task6.checkSubSequence(subSequence, sequence)).isEqualTo(true);
    }

    @ParameterizedTest
    @MethodSource("falseArguments")
    void falseInput(String subSequence, String sequence) {
        assertThat(Task6.checkSubSequence(subSequence, sequence)).isEqualTo(false);
    }

    @Test
    void nullInput() {
        assertThatThrownBy(() -> Task6.checkSubSequence(null, null)).isInstanceOf(IllegalArgumentException.class);
    }
}
