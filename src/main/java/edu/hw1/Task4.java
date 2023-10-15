package edu.hw1;

public class Task4 {

    public static String fixString(String string) {
        if (string == null || string.length() == 1) {
            return string;
        }

        var builder = new StringBuilder();
        char[] charArray = string.toCharArray();

        for (int i = 1; i < charArray.length; i += 2) {
            builder.append(charArray[i]);
            builder.append(charArray[i - 1]);
        }

        if (charArray.length % 2 == 1) {
            builder.append(charArray[charArray.length - 1]);
        }

        return builder.toString();
    }

    private Task4() {}
}
