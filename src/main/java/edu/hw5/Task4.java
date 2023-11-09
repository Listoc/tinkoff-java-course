package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    public static boolean checkPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Null input!");
        }

        var pattern = Pattern.compile(".*[~!@#$%^&*|].*");
        return pattern.matcher(password).matches();
    }


    private Task4() {}
}
