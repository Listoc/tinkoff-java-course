package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    public static boolean checkRegistration(String registration) {
        if (registration == null) {
            throw new IllegalArgumentException("Null input!");
        }

        // Используются только 12 букв кириллицы
        // Сначала одна буква, затем 3 любые цифры и ещё 2 буквы
        // Код региона может состоять из 2х любых цифр
        // Также код региона может состоять из 3х цифр, но тогда первая точно не 0
        var pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}[1-9]?\\d{2}$");
        return pattern.matcher(registration).matches();
    }

    private Task5() {}
}
