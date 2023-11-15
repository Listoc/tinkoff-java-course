package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    public static boolean checkSubSequence(String subSequence, String sequence) {
        if (subSequence == null || sequence == null) {
            throw new IllegalArgumentException("Null input!");
        }

        var array = subSequence.toCharArray();
        StringBuilder regex = new StringBuilder();

        for (var el : array) {
            regex.append(el);
            regex.append(".*");
        }

        var pattern = Pattern.compile(regex.toString());

        return pattern.matcher(sequence).find();
    }

    private Task6() {}
}
