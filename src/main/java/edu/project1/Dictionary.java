package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private final List<String> listOfWords = new ArrayList<>();
    private static final int MIN_LENGTH = 3;

    public Dictionary() {
        listOfWords.add("Basica");
    }

    public Dictionary(Iterable<String> source) {
        if (source == null) {
            throw new IllegalArgumentException("Null input");
        }

        for (var e : source) {
            if (e.length() >= MIN_LENGTH) {
                listOfWords.add(e);
            }
        }
    }

    public String getRandomWord() {
        if (listOfWords.isEmpty()) {
            return null;
        }

        int randomNumber = new Random().nextInt(0, listOfWords.size());

        return listOfWords.get(randomNumber);
    }

    public boolean addWord(String word) {
        if (word == null || word.length() < MIN_LENGTH) {
            return false;
        }

        listOfWords.add(word);

        return true;
    }
}
