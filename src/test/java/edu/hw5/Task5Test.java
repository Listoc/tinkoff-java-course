package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task5Test {
    static Arguments[] trueArguments() {
        return new Arguments[] {
            Arguments.of("А123ВЕ777"),
            Arguments.of("О777ОО177"),
            Arguments.of("В111КК01"),
        };
    }

    static Arguments[] falseArguments() {
        return new Arguments[] {
            Arguments.of("123АВЕ777"),
            Arguments.of("А123ВГ77"),
            Arguments.of("А123ВЕ7777"),
            Arguments.of(""),
            Arguments.of(" "),
            Arguments.of("someTextА123ВЕ777SomeTest"),
            Arguments.of("А123ВЕ077")
        };
    }

    @ParameterizedTest
    @MethodSource("trueArguments")
    void trueInput(String input) {
        assertThat(Task5.checkRegistration(input)).isEqualTo(true);
    }

    @ParameterizedTest
    @MethodSource("falseArguments")
    void falseInput(String input) {
        assertThat(Task5.checkRegistration(input)).isEqualTo(false);
    }

    @Test
    void nullInput() {
        assertThatThrownBy(() -> Task5.checkRegistration(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
