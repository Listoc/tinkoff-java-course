package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task7.rotateRight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static edu.hw1.Task7.rotateLeft;

public class Task7Test {
    @Test
    void rotateRightProperTest1() {
        assertThat(rotateRight(8, 1)).isEqualTo(4);
    }

    @Test
    void rotateRightProperTest2() {
        assertThat(rotateRight(8, 2)).isEqualTo(2);
    }

    @Test
    void rotateRightProperTest3() {
        assertThat(rotateRight(31, 5)).isEqualTo(31);
    }

    @Test
    void rotateLeftProperTest1() {
        assertThat(rotateLeft(16, 1)).isEqualTo(1);
    }

    @Test
    void rotateLeftProperTest2() {
        assertThat(rotateLeft(17, 2)).isEqualTo(6);
    }

    @Test
    void rotateLeftProperTest3() {
        assertThat(rotateLeft(31, 5)).isEqualTo(31);
    }

    @Test
    @DisplayName("Единица на входе")
    void oneInputTest() {
        assertThat(rotateLeft(1, 3)).isEqualTo(1);
    }

    @Test
    @DisplayName("Ноль на входе")
    void zeroInputTest() {
        assertThat(rotateLeft(0, 3)).isEqualTo(0);
    }

    @Test
    @DisplayName("Отрицательное число на входе")
    void negativeInputTest() {
        assertThat(rotateLeft(-17, 2)).isEqualTo(-6);
    }
}
