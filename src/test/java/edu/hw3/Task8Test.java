package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task8Test {
    @Test
    void properInput() {
        var iterator = new BackwardIterator<>(List.of(1, 2, 3));
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void usingNextWithoutElements() {
        var iterator = new BackwardIterator<>(List.of(1, 2, 3));

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThatThrownBy(() -> iterator.next()).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void nullCollectionInput() {
        assertThatThrownBy(() -> new BackwardIterator<>(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
