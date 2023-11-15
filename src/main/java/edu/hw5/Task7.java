package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    private static final String EXCEPTION_MESSAGE = "Null input!";

    public static boolean checkFirstRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern1 = Pattern.compile("[01]{2}0[01]*");

        return pattern1.matcher(input).matches();
    }

    public static boolean checkSecondRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern2 = Pattern.compile("0[01]*0|^1[01]*1|[01]");

        return pattern2.matcher(input).matches();
    }

    public static boolean checkThirdRegex(String input) {
        if (input == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        var pattern3 = Pattern.compile("[01]{1,3}");

        return pattern3.matcher(input).matches();
    }

    private Task7() {}
}
