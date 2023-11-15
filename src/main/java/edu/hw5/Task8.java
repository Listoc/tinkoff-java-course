package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private static final String EXCEPTION_MESSAGE = "Null input!";

    public static boolean checkFirstRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern = Pattern.compile("[01]([01]{2})*");

        return pattern.matcher(input).matches();
    }

    public static boolean checkSecondRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern = Pattern.compile("0([01]{2})*|1[01]([01]{2})*");

        return pattern.matcher(input).matches();
    }

    public static boolean checkThirdRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern = Pattern.compile("1*(1*01*01*01*)*");

        return pattern.matcher(input).matches();
    }

    public static boolean checkFourthRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern = Pattern.compile("(0|10|110|111[01])[01]*|1?");

        return pattern.matcher(input).matches();
    }

    public static boolean checkFifthRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern = Pattern.compile("(1[01])*1?");

        return pattern.matcher(input).matches();
    }

    public static boolean checkSixthRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern = Pattern.compile("0+1?0+|1?0{2,}|0{2,}1?");

        return pattern.matcher(input).matches();
    }

    public static boolean checkSeventhRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern = Pattern.compile("(0|10)*1?");

        return pattern.matcher(input).matches();
    }

    private Task8() {}
}
