package edu.hw1;

public class Task5 {

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
        char[] numberArray = (number + "").toCharArray();
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
        int multiplier = 1;
        int descendant = 0;
        int numberCopy = number;
        int digit;
        final int TEN = 10;

        if (Task2.countDigits(number) % 2 == 1) {
            multiplier = TEN;
            descendant = numberCopy % TEN;
            numberCopy /= TEN;
        }

        while (numberCopy > 0) {
            digit = numberCopy % TEN;
            numberCopy /= TEN;
            digit += numberCopy % TEN;
            numberCopy /= TEN;

            descendant += digit * multiplier;

            multiplier *= TEN;
            if (Task2.countDigits(digit) == 2) {
                multiplier *= TEN;
            }
        }

        return descendant;
    }

    private Task5() {}
}
