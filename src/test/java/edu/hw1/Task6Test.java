package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static edu.hw1.Task6.countK;

public class Task6Test {

    @Test
    void properInput1() {
        assertThat(countK(6621)).isEqualTo(5);
    }

    @Test
    void properInput2() {
        assertThat(countK(6554)).isEqualTo(4);
    }

    @Test
    void properInput3() {
        assertThat(countK(1234)).isEqualTo(3);
    }

    @Test
    void properInput4() {
        assertThat(countK(3524)).isEqualTo(3);
    }

    @Test
    @DisplayName("Число вне диапазона")
    void higherInput() {
        assertThat(countK(123)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Все цифры одинаковые")
    void sameDigits() {
        assertThat(countK(3333)).isEqualTo(-1);
    }

    @Test
    @DisplayName("6174 на входе")
    void input6174() {
        assertThat(countK(6174)).isEqualTo(0);
    }
}
