package edu.hw9.task1;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelStatsCollector implements StatsCollector {
    private final List<Stats> listOfStats;
    private final ExecutorService threadPool;

    public ParallelStatsCollector() {
        listOfStats = Collections.synchronizedList(new LinkedList<>());
        this.threadPool = Executors.newFixedThreadPool(3);
    }

    @Override
    public void push(@NotNull String metricName, double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Null array");
        }

        threadPool.submit(countStats(metricName, array));
    }

    private Runnable countStats(String name, double[] array) {
        return () -> {
            var stats = Arrays.stream(array).summaryStatistics();
            listOfStats.add(new Stats(name, stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax()));
        };
    }

    @Override
    public List<Stats> stats() {
        threadPool.close();
        return listOfStats;
    }
}
