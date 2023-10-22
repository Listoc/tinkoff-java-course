package edu.hw2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import edu.hw2.task2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle(20, 20)),
            Arguments.of(new Square(20))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        assertThat(rect.area()).isEqualTo(400.0);
    }

    @Test
    void negativeInput() {
        assertThatThrownBy(() -> new Rectangle(-5, 5)).isInstanceOf(IllegalArgumentException.class);
    }
}
