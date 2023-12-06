package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class AtomicCounter {
    private final AtomicLong counter = new AtomicLong(0);

    public void asyncIncrement(int incrementCount, int threadCount) throws InterruptedException {
        if (incrementCount < 0 || threadCount < 0) {
            throw new IllegalArgumentException();
        }

        int incrementForThread = incrementCount / threadCount;
        int incrementMod = incrementCount % threadCount;

        counter.addAndGet(incrementMod);

        var threadList = Stream.generate(
            () -> new Thread(threadIncrement(incrementForThread))
            )
            .limit(threadCount)
            .toList();

        threadList.forEach(Thread::start);

        for (var thread : threadList) {
            thread.join();
        }
    }

    private Runnable threadIncrement(int count) {
        return () -> {
            for (int i = 0; i < count; ++i) {
                counter.incrementAndGet();
            }
        };
    }

    public long getCounterValue() {
        return counter.longValue();
    }
}
