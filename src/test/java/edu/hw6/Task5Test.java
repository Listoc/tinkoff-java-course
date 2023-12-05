package edu.hw6;

import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    void newsProperInputTest() {
        assertThat(HackerNews.news(38258842)).isEqualTo("Amazon cuts games unit jobs");
    }

    @Test
    void newsNegativeInputTest() {
        assertThat(HackerNews.news(-1)).isEqualTo(null);
    }
}
