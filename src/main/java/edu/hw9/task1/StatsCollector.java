package edu.hw9.task1;

import java.util.List;

public interface StatsCollector {
    void push(String metricName, double[] array);
    List<Stats> stats();

    record Stats(String name, double sum, double avg, double min, double max) {}
}
