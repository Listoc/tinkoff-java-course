package edu.hw8;

import edu.hw8.task2.Fibonacci;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task2Test {
    @Nested
    class ThreadPoolTest {
        @Test
        void wrongCountOfThreadsInput() {
            assertThatThrownBy(() -> {
                try (var pool = FixedThreadPool.create(-5)) {

                }
            }).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void wrongRunnableInput() {
            assertThatThrownBy(() -> {
                try (var pool = FixedThreadPool.create(6)) {
                    pool.execute(null);
                }
            }).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class FibonacciTest {
        @RepeatedTest(1000)
        void properInput() {
            var expected = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89);

            assertThat(Fibonacci.getFibonacci(11)).isEqualTo(expected);
        }

        @Test
        void wrongInput() {
            assertThatThrownBy(() -> Fibonacci.getFibonacci(-5)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
