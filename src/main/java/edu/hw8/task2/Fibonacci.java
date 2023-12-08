package edu.hw8.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fibonacci {
    private static List<Integer> list;
    private static final int THREAD_COUNT = 6;

    public static List<Integer> getFibonacci(int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Too low count");
        }

        list = Collections.synchronizedList(new ArrayList<>());
        list.add(1);
        list.add(1);
        try (var pool = FixedThreadPool.create(THREAD_COUNT)) {
            pool.start();
            for (int i = 2; i < count; ++i) {
                pool.execute(fibonacci(i));
            }
        } catch (Exception ignored) {

        }

        return list;
    }

    private static Runnable fibonacci(int number) {
        return () -> recursiveFibonacci(number);
    }

    private synchronized static void recursiveFibonacci(int number) {
        if (list.size() > number) {
            return;
        }

        recursiveFibonacci(number - 1);
        recursiveFibonacci(number - 2);

        list.add(number, list.get(number - 1) + list.get(number - 2));
    }

    private Fibonacci() {}
}
