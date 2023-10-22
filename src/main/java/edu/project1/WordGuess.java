package edu.project1;

import java.util.Arrays;

public class WordGuess {
    private final String word;
    private final char[] guessState;
    private int usedAttempts;

    public WordGuess(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Null input");
        }

        for (int i = 0; i < word.length(); ++i) {
            if (!Character.isAlphabetic(word.charAt(i))) {
                throw new IllegalArgumentException("Use only alphabetic symbols");
            }
        }

        this.word = word.toLowerCase();
        this.guessState = new char[word.length()];
        Arrays.fill(guessState, '*');
        this.usedAttempts = 0;
    }

    public String getWord() {
        return word;
    }

    public String getCurrentGuessState() {
        return String.valueOf(guessState);
    }

    public boolean guess(char letter) {
        char lowerCaseLetter = Character.toLowerCase(letter);

        int index = -1;

        for (int i = 0; i < word.length(); ++i) {
            if (lowerCaseLetter == word.charAt(i)) {
                guessState[i] = lowerCaseLetter;
                index = i;
            }
        }

        if (index == -1) {
            usedAttempts++;
            return false;
        }

        return true;
    }

    public int getUsedAttempts() {
        return usedAttempts;
    }

    public boolean isGuessed() {
        return String.valueOf(guessState).equals(word);
    }
}
