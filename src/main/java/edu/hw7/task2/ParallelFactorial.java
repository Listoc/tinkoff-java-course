package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class ParallelFactorial {
    public static BigInteger getFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Negative number!");
        }

        if (number == 0 || number == 1) {
            return BigInteger.ONE;
        }

        return LongStream
            .range(2, number + 1)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    private ParallelFactorial() {}
}
