package edu.hw1;

public class Task5 {
    private static final int TEN = 10;

    public static boolean isPalindromeDescendant(int number) {
        return isPalindromeDescendantRecursion(Math.abs(number));
    }

    private static boolean isPalindromeDescendantRecursion(int number) {
        if (Task2.countDigits(number) < 2) {
            return false;
        }

        if (isPalindrome(number)) {
            return true;
        }

        return isPalindromeDescendantRecursion(getDescendant(number));
    }

    private static boolean isPalindrome(int number) {
        char[] numberArray = String.valueOf(number).toCharArray();
        int i = 0;
        int j = numberArray.length - 1;
        while (i < j) {
            if (numberArray[i] != numberArray[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    private static int getDescendant(int number) {
        StringBuilder descendant = new StringBuilder();
        int numberCopy = number;
        int digit;

        if (Task2.countDigits(number) % 2 == 1) {
            descendant.append(numberCopy % TEN);
            numberCopy /= TEN;
        }

        while (numberCopy > 0) {
            digit = numberCopy % TEN;
            numberCopy /= TEN;
            digit += numberCopy % TEN;
            numberCopy /= TEN;

            descendant.insert(0, digit);
        }

        return Integer.parseInt(descendant.toString());
    }

    private Task5() {}
}
