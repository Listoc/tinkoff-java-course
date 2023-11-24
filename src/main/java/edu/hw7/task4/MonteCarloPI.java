package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MonteCarloPI {
    private static final long RADIUS = 20000000;

    private static AtomicInteger circleCount;

    @SuppressWarnings("MagicNumber")
    public static double getPI(int threadCount, int totalCount) {
        if (threadCount < 1 || totalCount < threadCount) {
            throw new IllegalArgumentException();
        }

        circleCount = new AtomicInteger(0);

        int countPerThread = totalCount / threadCount;
        int mod = totalCount % threadCount;

        List<Thread> list = new ArrayList<>();
        list.add(new Thread(threadPIComputing(countPerThread + mod)));

        list.addAll(
            Stream.generate(() -> new Thread(threadPIComputing(countPerThread)))
                .limit(threadCount - 1)
                .toList()
        );

        list.forEach(Thread::start);

        try {
            for (var thread : list) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 4.0 * ((double) circleCount.get() / (double) totalCount);
    }

    private static Runnable threadPIComputing(int count) {
        return () -> {
            int threadCircleCount = 0;
            for (int i = 0; i < count; ++i) {
                if (Point.randomPoint(RADIUS).inCircle(RADIUS)) {
                    threadCircleCount++;
                }
            }

            circleCount.addAndGet(threadCircleCount);
        };
    }

//    public static void main(String[] args) {
//        var start = System.nanoTime();
//        System.out.println("PI = " + getPI(1, 1_000_000_000));
//        var end = System.nanoTime();
//
//        var timeOfOneThread = end - start;
//
//        System.out.println("Thread count: " + 1);
//        System.out.println("Time: " + timeOfOneThread / Math.pow(10, 9)  + " сек");
//        System.out.println();
//
//        for (int i = 2; i < 13; i += 2) {
//            start = System.nanoTime();
//            System.out.println("PI = " + getPI(i, 1_000_000_000));
//            end = System.nanoTime();
//
//            System.out.println("Thread count: " + i);
//            System.out.println("Time: " + (end - start) / Math.pow(10, 9) + " сек");
//            System.out.println((double) timeOfOneThread / (end - start) + " times faster then one thread");
//            System.out.println();
//        }
//
//        double pi1 = getPI(6, 1_000_000);
//        double pi2 = getPI(6, 10_000_000);
//        double pi3 = getPI(6, 100_000_000);
//        double pi4 = getPI(6, 1_000_000_000);
//
//        System.out.println("1млн   pi: " + pi1);
//        System.out.println("10млн  pi: " + pi2);
//        System.out.println("100млн pi: " + pi3);
//        System.out.println("1млрд  pi: " + pi4);
//
//        System.out.println();
//
//        System.out.println("1млн   погрешность%: " + Math.abs(Math.PI / pi1 * 100 - 100));
//        System.out.println("10млн  погрешность%: " + Math.abs(Math.PI / pi2 * 100 - 100));
//        System.out.println("100млн погрешность%: " + Math.abs(Math.PI / pi3 * 100 - 100));
//        System.out.println("1млрд  погрешность%: " + Math.abs(Math.PI / pi4 * 100 - 100));
//    }

    private MonteCarloPI() {}
}
