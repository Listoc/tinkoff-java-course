package edu.hw1;

public class Task1 {
    private static final int SECONDS_IN_MINUTE = 60;

    public static int minuteToSeconds(String time) {
        if (time == null) {
            return -1;
        }

        String[] split = time.split(":");

        if (split.length != 2 || split[0].length() < 2 || split[1].length() != 2) {
            return -1;
        }

        int seconds;
        int minutes;
        int result = 0;

        char[] firstNumberArray = split[0].toCharArray();

        for (var el : firstNumberArray) {
            if (!Character.isDigit(el)) {
                result = -1;
            }
        }

        if (!Character.isDigit(split[1].charAt(0)) || !Character.isDigit(split[1].charAt(1))) {
            result = -1;
        }

        if (result != -1) {
            minutes = Integer.parseInt(split[0]);
            seconds = Integer.parseInt(split[1]);

            if (minutes < 0 || seconds < 0 || seconds >= SECONDS_IN_MINUTE) {
                result = -1;
            } else {
                result = minutes * SECONDS_IN_MINUTE + seconds;
            }
        }

        return result;

    }

    private Task1() {}
}
