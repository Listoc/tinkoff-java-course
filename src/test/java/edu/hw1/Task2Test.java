package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task2.countDigits;

public class Task2Test {
    @Test
    void properInput1() {
        assertThat(countDigits(10)).isEqualTo(2);
    }

    @Test
    void properInput2() {
        assertThat(countDigits(5)).isEqualTo(1);
    }

    @Test
    void properInput3() {
        assertThat(countDigits(321)).isEqualTo(3);
    }

    @Test
    void properInput4() {
        assertThat(countDigits(5010)).isEqualTo(4);
    }

    @Test
    @DisplayName("Ввод 0")
    void zeroInput() {
        assertThat(countDigits(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательное число")
    void negativeInput1() {
        assertThat(countDigits(-112)).isEqualTo(3);
    }

    @Test
    @DisplayName("Отрицательное число")
    void negativeInput2() {
        assertThat(countDigits(-10)).isEqualTo(2);
    }
}
