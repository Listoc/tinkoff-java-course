package edu.hw3;

import edu.hw3.task3.Task3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    static Arguments[] input() {
        return new Arguments[] {
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2))
        };
    }

    @ParameterizedTest
    @MethodSource("input")
    void properInput(List<Object> input, Map<Object, Integer> map) {
        assertThat(Task3.freqDict(input)).isEqualTo(map);
    }

    @Test
    void nullInput() {
        assertThat(Task3.freqDict(null)).isEqualTo(Map.of());
    }

}
