package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private static final int TEN = 10;

    public static int countK(int number) {
        final int LOW_BOUND = 1000;
        final int HIGH_BOUND = 9999;
        if (number <= LOW_BOUND || number >= HIGH_BOUND) {
            return -1;
        }

        if (checkAllDigitsIsSame(number)) {
            return -1;
        }

        return countKRecursion(number);
    }

    @SuppressWarnings("MagicNumber")
    private static int countKRecursion(int number) {
        if (number == 6174) {
            return 0;
        }

        int[] array = toArray(number);
        Arrays.sort(array);

        int max = getMax(array);
        int min = getMin(array);

        return countKRecursion(max - min) + 1;
    }

    private static int getMin(int[] array) {
        int result = 0;
        int multiplier = 1;

        for (int i = array.length - 1; i > -1; --i) {
            result += multiplier * array[i];
            multiplier *= TEN;
        }

        return result;
    }

    private static int getMax(int[] array) {
        int result = 0;
        int multiplier = 1;

        for (int i = 0; i < array.length; ++i) {
            result += multiplier * array[i];
            multiplier *= TEN;
        }

        return result;
    }

    private static int[] toArray(int number) {
        final int DIGIT_COUNT = 4;
        int numberCopy = number;
        int[] array = new int[DIGIT_COUNT];
        int i = 0;

        while (numberCopy > 0) {
            array[i] = numberCopy % TEN;
            numberCopy /= TEN;
            i++;
        }

        return array;
    }

    private static boolean checkAllDigitsIsSame(int number) {
        int numberCopy = number;
        int digit = numberCopy % TEN;

        while (numberCopy > 0) {
            if (numberCopy % TEN != digit) {
                return false;
            }

            numberCopy /= TEN;
        }

        return true;
    }

    private Task6() {}
}
