package edu.hw3.task4;

import java.util.TreeMap;

@SuppressWarnings("MagicNumber")
public class Task4 {
    private static final TreeMap<Integer, String> ARABIC_TO_ROMAN;

    static {
        ARABIC_TO_ROMAN = new TreeMap<>();
        ARABIC_TO_ROMAN.put(1, "I");
        ARABIC_TO_ROMAN.put(4, "IV");
        ARABIC_TO_ROMAN.put(5, "V");
        ARABIC_TO_ROMAN.put(9, "IX");
        ARABIC_TO_ROMAN.put(10, "X");
        ARABIC_TO_ROMAN.put(40, "XL");
        ARABIC_TO_ROMAN.put(50, "L");
        ARABIC_TO_ROMAN.put(90, "XC");
        ARABIC_TO_ROMAN.put(100, "C");
        ARABIC_TO_ROMAN.put(400, "CD");
        ARABIC_TO_ROMAN.put(500, "D");
        ARABIC_TO_ROMAN.put(900, "CM");
        ARABIC_TO_ROMAN.put(1000, "M");
    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > 4999) {
            return null;
        }

        var builder = new StringBuilder();
        int floorKey;
        int numberCopy = number;

        while (numberCopy > 0) {
            floorKey = ARABIC_TO_ROMAN.floorKey(numberCopy);
            builder.append(ARABIC_TO_ROMAN.get(floorKey));
            numberCopy -= floorKey;
        }

        return builder.toString();
    }

    private Task4() {}
}
