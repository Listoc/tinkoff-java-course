package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task5.isPalindromeDescendant;

public class Task5Test {

    @Test
    void properInput1() {
        assertThat(isPalindromeDescendant(11211230)).isEqualTo(true);
    }

    @Test
    void properInput2() {
        assertThat(isPalindromeDescendant(13001120)).isEqualTo(true);
    }

    @Test
    void properInput3() {
        assertThat(isPalindromeDescendant(23336014)).isEqualTo(true);
    }

    @Test
    void properInput4() {
        assertThat(isPalindromeDescendant(11)).isEqualTo(true);
    }

    @Test
    void properInput5() {
        assertThat(isPalindromeDescendant(3445443)).isEqualTo(true);
    }

    @Test
    void properInput6() {
        assertThat(isPalindromeDescendant(136)).isEqualTo(false);
    }

    @Test
    @DisplayName("Один разряд на входе")
    void oneDigit() {
        assertThat(isPalindromeDescendant(3)).isEqualTo(false);
    }
}
