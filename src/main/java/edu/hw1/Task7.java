package edu.hw1;

public class Task7 {

    public static int rotateRight(int n, int shift) {
        final int INTEGER_BITS = 32;
        int[] array = new int[INTEGER_BITS];
        int trueLength = toBinaryArray(n, array);
        boolean isNegative = n < 0;
        int firstDigit;

        for (int i = 0; i < shift; ++i) {
            firstDigit = array[0];
            for (int j = 0; j < trueLength; ++j) {
                array[j] = array[j + 1];
            }
            array[trueLength] = firstDigit;
        }


        int resultNumber = toNumber(array, trueLength);

        return isNegative ? -resultNumber : resultNumber;
    }

    public static int rotateLeft(int n, int shift) {
        final int INTEGER_BITS = 32;
        int[] array = new int[INTEGER_BITS];
        int trueLength = toBinaryArray(n, array);
        boolean isNegative = n < 0;
        int firstDigit;

        for (int i = 0; i < shift; ++i) {
            firstDigit = array[trueLength];
            for (int j = trueLength; j > 0; --j) {
                array[j] = array[j - 1];
            }
            array[0] = firstDigit;
        }


        int resultNumber = toNumber(array, trueLength);

        return isNegative ? -resultNumber : resultNumber;
    }

    private static int toBinaryArray(int number, int[] array) {
        int numberCopy = Math.abs(number);
        int i = 0;
        int trueLength = 0;

        while (numberCopy > 0) {
            array[i] = numberCopy % 2;
            if (array[i] == 1) {
                trueLength = i;
            }
            numberCopy /= 2;
            i++;
        }

        return trueLength;
    }

    private static int toNumber(int[] array, int trueLength) {
        int number = 0;

        for (int i = 0; i < trueLength + 1; ++i) {
            number += array[i] * (int) Math.pow(2, i);
        }

        return number;
    }

    private Task7() {}
}
