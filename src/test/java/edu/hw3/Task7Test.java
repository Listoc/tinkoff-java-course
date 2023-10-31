package edu.hw3;

import edu.hw3.task7.Task7;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void properTest() {
        TreeMap<String, String> tree = new TreeMap<>(Task7.getNullComparator());

        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
