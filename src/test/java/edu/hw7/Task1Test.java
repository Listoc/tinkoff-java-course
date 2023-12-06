package edu.hw7;

import edu.hw7.task1.AtomicCounter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task1Test {
    @Test
    void properInput() throws InterruptedException {
        var counter = new AtomicCounter();

        counter.asyncIncrement(1000, 4);
        counter.asyncIncrement(1000, 2);
        counter.asyncIncrement(1000, 3);

        assertThat(counter.getCounterValue()).isEqualTo(3000L);
    }

    @Test
    void wrongInput() {
        var counter = new AtomicCounter();

        assertThatThrownBy(() -> counter.asyncIncrement(-5, -4)).isInstanceOf(IllegalArgumentException.class);
    }
}
