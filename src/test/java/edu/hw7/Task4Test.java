package edu.hw7;

import edu.hw7.task4.MonteCarloPI;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task4Test {
    @Test
    void properInput() {
        assertThat(MonteCarloPI.getPI(4, 1_000_000)).isCloseTo(Math.PI, Offset.offset(0.01));
    }

    @Test
    void wrongInput() {
        assertThatThrownBy(() -> MonteCarloPI.getPI(-5, -10)).isInstanceOf(IllegalArgumentException.class);
    }
}
