package edu.project1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import edu.project1.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Project1Test {
    @Nested
    class WordGuessTest {
        @Test
        void guessGoodTest() {
            var word = new WordGuess("test");

            assertThat(word.guess('e')).isEqualTo(true);
        }

        @Test
        void guessBadTest() {
            var word = new WordGuess("test");

            assertThat(word.guess('g')).isEqualTo(false);
        }

        @Test
        void twoLettersTest() {
            var word = new WordGuess("test");

            word.guess('t');

            assertThat(word.getCurrentGuessState()).isEqualTo("t**t");
        }

        @Test
        void fullGuessedTest() {
            var word = new WordGuess("test");

            word.guess('t');
            word.guess('e');
            word.guess('s');

            assertThat(word.getCurrentGuessState()).isEqualTo("test");
        }

        @Test
        void getUsedAttemptsTest() {
            var word = new WordGuess("test");

            word.guess('g');
            word.guess('f');
            word.guess('t');

            assertThat(word.getUsedAttempts()).isEqualTo(2);
        }

        @Test
        void isGuessedGoodTest() {
            var word = new WordGuess("test");

            word.guess('t');
            word.guess('e');
            word.guess('s');

            assertThat(word.isGuessed()).isEqualTo(true);
        }

        @Test
        void isGuessedBadTest() {
            var word = new WordGuess("test");

            word.guess('t');

            assertThat(word.isGuessed()).isEqualTo(false);
        }

        @Test
        void nullTest() {
            assertThatThrownBy(() -> new WordGuess(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void badWordTest() {
            assertThatThrownBy(() -> new WordGuess("abs2c")).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
