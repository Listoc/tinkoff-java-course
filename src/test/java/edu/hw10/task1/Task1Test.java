package edu.hw10.task1;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task1Test {
    @RepeatedTest(50)
    void recordTest() {
        var generator = new RandomObjectGenerator();

        var test = (TestRecord) generator.nextObject(TestRecord.class);

        assertThat(test.a2() >= 50 && test.a2() < 100).isTrue();
    }

    @RepeatedTest(50)
    void classFactoryTest() {
        var generator = new RandomObjectGenerator();

        var test = (TestClass) generator.nextObject(TestClass.class, "of");

        assertThat(test.getA2() >= 50 && test.getA2() < 100).isTrue();
    }

    @RepeatedTest(200)
    void notNullTestTest() {
        var generator = new RandomObjectGenerator();

        var test = (TestClass) generator.nextObject(TestClass.class, "of");

        assertThat(test.getA5()).isNotNull();
    }

    @Test
    void noConstructorTest() {
        var generator = new RandomObjectGenerator();

        assertThatThrownBy(() -> generator.nextObject(TestClass.class)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void noFactoryMethod() {
        var generator = new RandomObjectGenerator();

        assertThatThrownBy(() -> generator.nextObject(TestClass.class, "noSuchMethod")).isInstanceOf(IllegalArgumentException.class);
    }

}
