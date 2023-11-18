package edu.hw6;

import edu.hw6.task6.PortsChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task6Test {
    static Arguments[] input() {
        return new Arguments[] {
            Arguments.of(100, 50),
            Arguments.of(-100, 50),
            Arguments.of(100, -50),
            Arguments.of(0, 500000)
        };
    }

    @ParameterizedTest
    @MethodSource("input")
    void wrongInput(int start, int end) {
        assertThatThrownBy(() -> PortsChecker.getPorts(start, end)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("input")
    void properInput() {
        assertThatNoException().isThrownBy(() -> PortsChecker.getPorts(1, 100));
    }

}
