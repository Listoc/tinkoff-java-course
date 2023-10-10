package edu.hw1;

public class Task2 {
    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int resultCount = 0;
        int n = Math.abs(number);
        final int DIVIDER = 10;

        while (n > 0) {
            n /= DIVIDER;
            resultCount++;
        }

        return resultCount;
    }

    private Task2() {}
}
