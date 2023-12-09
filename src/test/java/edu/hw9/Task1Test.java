package edu.hw9;

import edu.hw9.task1.ParallelStatsCollector;
import edu.hw9.task1.StatsCollector;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task1Test {
    @Test
    void normalWorkTest() {
        var expected = List.of(
            new StatsCollector.Stats("metric1", 13, 3.25, 1, 5),
            new StatsCollector.Stats("metric3", 3.6, 1.8, 0.5, 3.1),
            new StatsCollector.Stats("metric2", 137.8, 17.225, 0.1, 100.7),
            new StatsCollector.Stats("unoMetric", 3.5, 3.5, 3.5, 3.5)
        );

        var collector = new ParallelStatsCollector();

        try (
            var pool = Executors.newFixedThreadPool(3);
        ) {
            pool.submit(() -> collector.push("metric1", new double[]{5, 1, 3, 4}));
            pool.submit(() -> collector.push("metric3", new double[]{3.1, 0.5}));
            pool.submit(() -> collector.push("metric2", new double[]{10, 5.0, 4.3, 100.7, 1, 4, 12.7, 0.1}));
            pool.submit(() -> collector.push("unoMetric", new double[]{3.5}));
        }

        assertThat(collector.stats()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void nullArrayInput() {
        var collector = new ParallelStatsCollector();
        assertThatThrownBy(() -> collector.push("SomeName", null)).isInstanceOf(IllegalArgumentException.class);
    }
}
