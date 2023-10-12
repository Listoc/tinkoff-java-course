package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static edu.hw1.Task8.knightBoardCapture;

public class Task8Test {

    @Test
    void properInput1() {
        int[][] array = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        assertThat(knightBoardCapture(array)).isEqualTo(true);
    }

    @Test
    void properInput2() {
        int[][] array = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        assertThat(knightBoardCapture(array)).isEqualTo(false);
    }

    @Test
    void properInput3() {
        int[][] array = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        assertThat(knightBoardCapture(array)).isEqualTo(false);
    }

    @Test
    @DisplayName("Null на входе")
    void nullTest() {
        assertThatThrownBy(() -> knightBoardCapture(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Меньший размер массива")
    void wrongSize1() {
        int[][] array = {{0}, {1}, {2}};

        assertThatThrownBy(() -> knightBoardCapture(array)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Больший размер массива")
    void wrongSize2() {
        int[][] array = {{0}, {1}, {2}, {0}, {1}, {2}, {0}, {1}, {2}, {0}, {1}, {2}};

        assertThatThrownBy(() -> knightBoardCapture(array)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Неправильный размер подмассива")
    void wrongSize3() {
        int[][] array = {{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}};

        assertThatThrownBy(() -> knightBoardCapture(array)).isInstanceOf(IllegalArgumentException.class);
    }
}

