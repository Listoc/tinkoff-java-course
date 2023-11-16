package edu.hw6;

import edu.hw6.task4.Task4;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task4Test {
    @BeforeAll
    @AfterAll
    static void clear() throws IOException {
        Files.deleteIfExists(Path.of("test.txt"));
    }

    @Test
    void properInput() throws IOException {
        var path = Path.of("test.txt");
        Task4.print(path);
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        String result;
        try (var reader = new BufferedReader(new FileReader("test.txt"))) {
            result = reader.readLine();
        }
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void nullInput() {
        assertThatThrownBy(() -> Task4.print(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void directoryInput() {
        assertThatThrownBy(() -> Task4.print(Path.of("src"))).isInstanceOf(IllegalArgumentException.class);
    }

}
