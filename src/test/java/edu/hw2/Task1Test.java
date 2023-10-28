package edu.hw2;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import edu.hw2.task1.*;

public class Task1Test {
    @Nested
    class ConstantTest {
        @Test
        void positiveInput() {
            assertThat(new Constant(5).evaluate()).isEqualTo(5);
        }

        @Test
        void negativeInput() {
            assertThat(new Constant(-5).evaluate()).isEqualTo(-5);
        }

        @Test
        void positiveDecimalInput() {
            assertThat(new Constant(2.3).evaluate()).isEqualTo(2.3);
        }

        @Test
        void negativeDecimalInput() {
            assertThat(new Constant(-2.3).evaluate()).isEqualTo(-2.3);
        }
    }

    @Nested
    class NegateTest {
        @Test
        void positiveInput() {
            var two = new Constant(2);

            assertThat(new Negate(two).evaluate()).isEqualTo(-2);
        }

        @Test
        void negativeInput() {
            var two = new Constant(-2);

            assertThat(new Negate(two).evaluate()).isEqualTo(2);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> new Negate(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class AdditionTest {
        @Test
        void positiveInput() {
            var two = new Constant(2);
            var four = new Constant(4);

            assertThat(new Addition(two, four).evaluate()).isEqualTo(6);
        }

        @Test
        void negativeInput() {
            var two = new Constant(2);
            var four = new Constant(4);
            var minusFour = new Negate(four);

            assertThat(new Addition(two, minusFour).evaluate()).isEqualTo(-2);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> new Addition(null, null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class MultiplicationTest {
        @Test
        void positiveInput() {
            var two = new Constant(2);
            var six = new Constant(6);

            assertThat(new Multiplication(two, six).evaluate()).isEqualTo(12);
        }

        @Test
        void negativeInput() {
            var minusTwo = new Constant(-2);
            var six = new Constant(6);

            assertThat(new Multiplication(minusTwo, six).evaluate()).isEqualTo(-12);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> new Multiplication(null, null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class ExponentTest {
        @Test
        void positiveInput() {
            var two = new Constant(2);

            assertThat(new Exponent(two, 4).evaluate()).isEqualTo(16);
        }

        @Test
        void negativeInput() {
            var minusTwo = new Constant(-2);

            assertThat(new Exponent(minusTwo, 4).evaluate()).isEqualTo(16);
        }

        @Test
        void nullInput() {
            assertThatThrownBy(() -> new Exponent(null, 2)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void mainTest() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }
}
