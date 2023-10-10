package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task2.countDigits;

public class Task2Test {
    @Test
    @DisplayName("Нормальный ввод")
    void properInput() {
        assertThat(countDigits(10)).isEqualTo(2);
        assertThat(countDigits(5)).isEqualTo(1);
        assertThat(countDigits(321)).isEqualTo(3);
        assertThat(countDigits(5010)).isEqualTo(4);
    }

    @Test
    @DisplayName("Ввод 0")
    void zeroInput() {
        assertThat(countDigits(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательный ввод")
    void negativeInput() {
        assertThat(countDigits(-10)).isEqualTo(2);
        assertThat(countDigits(-112)).isEqualTo(3);
    }
}
