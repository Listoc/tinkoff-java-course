package edu.hw11;

import edu.hw11.task1.Task1;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void properTest() throws Exception {
        assertThat(new Task1().helloByteBuddy().getDeclaredConstructor().newInstance().toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
