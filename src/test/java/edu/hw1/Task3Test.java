package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task3.isNestable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {
    @Test
    void properInput1() {
        assertThat(isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6})).isEqualTo(true);
    }

    @Test
    void properInput2() {
        assertThat(isNestable(new int[]{3, 1}, new int[]{4, 0})).isEqualTo(true);
    }

    @Test
    void properInput3() {
        assertThat(isNestable(new int[]{9, 9, 8}, new int[]{8, 9})).isEqualTo(false);
    }

    @Test
    void properInput4() {
        assertThat(isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3})).isEqualTo(false);
    }

    @Test
    @DisplayName("Пустой массив на входе")
    void emptyArray() {
        assertThatThrownBy(() -> isNestable(new int[0], new int[]{1, 2})).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> isNestable(new int[]{1, 2, 3}, new int[0])).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Null на входе")
    void nullArray() {
        assertThatThrownBy(() -> isNestable(new int[]{1, 2, 3}, null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Один элемент во втором массиве")
    void oneLength1() {
        assertThat(isNestable(new int[]{4, 0}, new int[]{3})).isEqualTo(false);
    }

    @Test
    @DisplayName("Один элемент во втором массиве")
    void oneLength2() {
        assertThat(isNestable(new int[]{4, 0}, new int[]{5})).isEqualTo(false);
    }

    @Test
    @DisplayName("Один элемент во втором массиве")
    void oneLength3() {
        assertThat(isNestable(new int[]{4, 0}, new int[]{-1})).isEqualTo(false);
    }
}
