package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> queue;
    private boolean working;
    private static final int TIME_OUT = 100;

    public static FixedThreadPool create(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Too few threads");
        }

        return new FixedThreadPool(count);
    }

    private FixedThreadPool(int count) {
        this.threads = new Thread[count];
        for (int i = 0; i < count; ++i) {
            threads[i] = new Thread(getThreadRunnable());
        }
        this.queue = new LinkedBlockingQueue<>();
        working = true;
    }

    @Override
    public void start() {
        for (var el : threads) {
            el.start();
        }
    }

    private Runnable getThreadRunnable() {
        return () -> {
            Runnable task;
            while (working || !queue.isEmpty()) {
                try {
                    task = queue.poll(TIME_OUT, TimeUnit.MICROSECONDS);

                    if (task == null) {
                        continue;
                    }

                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Null runnable!");
        }

        queue.add(runnable);
    }

    @Override
    public void close() throws Exception {
        working = false;
        for (var el : threads) {
            el.join();
        }
    }
}
