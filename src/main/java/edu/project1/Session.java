package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Session {
    private final static Logger LOGGER = LogManager.getLogger();
    private final WordGuess currentWordGuess;
    private final int maxAttempts;

    public Session(String word, int maxAttempts) {
        if (word == null) {
            throw new IllegalArgumentException("Null на входе!");
        }

        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Слишком мало попыток!");
        }

        this.currentWordGuess = new WordGuess(word);
        this.maxAttempts = maxAttempts;
    }

    public boolean isLost() {
        return currentWordGuess.getUsedAttempts() >= maxAttempts;
    }

    public boolean isWon() {
        return currentWordGuess.isGuessed();
    }

    public boolean isOver() {
        return isLost() || isWon();
    }

    private void printOverMessage() {
        if (isLost()) {
            LOGGER.info("Вы проиграл!");
        } else if (isWon()) {
            LOGGER.info("Вы победили!");
        }
    }

    public void guess() {
        char letter = getLetter();

        if (isOver()) {
            printOverMessage();
            return;
        }

        if (letter != '!') {
            boolean result = currentWordGuess.guess(letter);

            if (result) {
                LOGGER.info("Верно!");
            } else {
                LOGGER.info("Ошибка, осталось " + (maxAttempts - currentWordGuess.getUsedAttempts()) + " попыток.");
            }

            LOGGER.info("Слово: " + currentWordGuess.getCurrentGuessState());

            if (isOver()) {
                printOverMessage();
            }
        } else {
            LOGGER.info("Некорректный ввод!");
        }
    }

    private char getLetter() {
        LOGGER.info("Введите букву: ");
        var in = new Scanner(System.in);
        String inputString = in.next();

        if (inputString.equalsIgnoreCase("стоп")) {
            endGame();
        }

        if (inputString.length() == 1) {
            char letter = inputString.toCharArray()[0];
            if (Character.isAlphabetic(letter)) {
                return letter;
            }
        }

        return '!';
    }

    private void endGame() {
        while (!isLost()) {
            currentWordGuess.guess('!');
        }
    }
}
