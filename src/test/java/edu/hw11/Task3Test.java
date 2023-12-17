package edu.hw11;

import edu.hw11.task3.Task3;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    void properTest() throws Exception {
        var fibClass = Task3.getClassFib();
        var instance = fibClass.getConstructor().newInstance();
        var method = fibClass.getMethod("fib", int.class);

        assertThat(method.invoke(instance, 3)).isEqualTo(2L);
    }

    @Test
    void properTest2() throws Exception {
        var fibClass = Task3.getClassFib();
        var instance = fibClass.getConstructor().newInstance();
        var method = fibClass.getMethod("fib", int.class);

        assertThat(method.invoke(instance, 5)).isEqualTo(5L);
    }

    @Test
    void properTest3() throws Exception {
        var fibClass = Task3.getClassFib();
        var instance = fibClass.getConstructor().newInstance();
        var method = fibClass.getMethod("fib", int.class);

        assertThat(method.invoke(instance, 10)).isEqualTo(55L);
    }
}
