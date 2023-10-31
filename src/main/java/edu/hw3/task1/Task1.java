package edu.hw3.task1;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    private static final Map<Character, Character> LETTERS_MAP;

    static {
        LETTERS_MAP = new HashMap<>();
        for (char i = 'a'; i <= 'z'; ++i) {
            LETTERS_MAP.put(i, (char) ('z' - (i - 'a')));
            LETTERS_MAP.put(Character.toUpperCase(i), Character.toUpperCase((char) ('z' - (i - 'a'))));
        }
    }

    public static String atbash(String str) {
        if (str == null) {
            return null;
        }

        var charArray = str.toCharArray();
        var builder = new StringBuilder();

        for (var ch : charArray) {
            if (LETTERS_MAP.containsKey(ch)) {
                builder.append(LETTERS_MAP.get(ch));
            } else {
                builder.append(ch);
            }
        }

        return builder.toString();
    }

    private Task1() {}
}
