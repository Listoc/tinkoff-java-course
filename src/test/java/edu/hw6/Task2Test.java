package edu.hw6;

import edu.hw6.task2.ClonerUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task2Test {
    @BeforeEach
    @AfterEach
    void clear() throws IOException {
        Files.deleteIfExists(Path.of("test.txt"));
        Files.deleteIfExists(Path.of("test - копия.txt"));
        Files.deleteIfExists(Path.of("test - копия (2).txt"));
        Files.deleteIfExists(Path.of("test.mobi.fb2"));
        Files.deleteIfExists(Path.of("test - копия.mobi.fb2"));
        Files.deleteIfExists(Path.of("test - копия (2).mobi.fb2"));
        Files.deleteIfExists(Path.of("test - копия (3).txt"));
        Files.deleteIfExists(Path.of("test"));
        Files.deleteIfExists(Path.of("test - копия"));
    }

    @AfterAll
    @BeforeAll
    static void clearAfter() throws IOException {
        Files.deleteIfExists(Path.of("test.txt"));
        Files.deleteIfExists(Path.of("test - копия.txt"));
        Files.deleteIfExists(Path.of("test - копия (2).txt"));
        Files.deleteIfExists(Path.of("test - копия (3).txt"));
        Files.deleteIfExists(Path.of("test"));
        Files.deleteIfExists(Path.of("test - копия"));
        Files.deleteIfExists(Path.of("test - копия (2)"));
    }

    @Test
    void noSuchFileTest() throws IOException {
        var path = Path.of("test.txt");
        assertThat(ClonerUtil.cloneFile(path)).isEqualTo(path);
    }

    @Test
    void firstCopyTest() throws IOException {
        var path = Path.of("test.txt");
        ClonerUtil.cloneFile(path);
        assertThat(ClonerUtil.cloneFile(path)).isEqualTo(Path.of("test - копия.txt"));
    }

    @Test
    void secondCopyTest() throws IOException {
        var path = Path.of("test.txt");
        ClonerUtil.cloneFile(path);
        ClonerUtil.cloneFile(path);
        var result = ClonerUtil.cloneFile(path);
        assertThat(result).isEqualTo(Path.of("test - копия (2).txt"));
    }

    @Test
    void twoDotsSecondCopyTest() throws IOException {
        var path = Path.of("test.mobi.fb2");
        ClonerUtil.cloneFile(path);
        ClonerUtil.cloneFile(path);
        var result = ClonerUtil.cloneFile(path);
        assertThat(result).isEqualTo(Path.of("test - копия (2).mobi.fb2"));
    }

    @Test
    void nullInputTest() {
        assertThatThrownBy(() -> ClonerUtil.cloneFile(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void directoryInputTest() {
        assertThatThrownBy(() -> ClonerUtil.cloneFile(Path.of("src"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void withoutPostFixTest() throws IOException {
        var path = Path.of("test");
        ClonerUtil.cloneFile(path);
        assertThat(ClonerUtil.cloneFile(path)).isEqualTo(Path.of("test - копия"));
    }
}
