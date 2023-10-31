package edu.hw3;

import edu.hw3.task2.Task2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] properInputs() {
        return new Arguments[] {
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        };
    }

    static Arguments[] wrongInputs() {
        return new Arguments[] {
            Arguments.of("()()()("),
            Arguments.of("((())))"),
            Arguments.of("(a)(b)(c)"),
            Arguments.of(")()("),
            Arguments.of(")"),
            Arguments.of("(")
        };
    }


    @ParameterizedTest
    @MethodSource("properInputs")
    void properInput(String input, List<String> list) {

        assertThat(Task2.clusterize(input)).isEqualTo(list);
    }

    @Test
    void nullInput() {
        assertThat(Task2.clusterize(null)).isEqualTo(List.of());
    }

    @ParameterizedTest
    @MethodSource("wrongInputs")
    void wrongInput(String input) {
        assertThat(Task2.clusterize(input)).isEqualTo(List.of());
    }

}
