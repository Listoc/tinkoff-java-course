package edu.hw6;

import edu.hw6.task3.AbstractFilter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private static final Path path = Path.of("src/test/java/edu/hw6");

    @BeforeAll
    @AfterAll
    static void clear() throws IOException {
        Files.deleteIfExists(path.resolve("unreadable"));
        Files.deleteIfExists(path.resolve("test.txt"));
    }

    @AfterEach
    @BeforeEach
    void clearEach() throws IOException {
        Files.deleteIfExists(path.resolve("unreadable"));
        Files.deleteIfExists(path.resolve("test.txt"));
    }

    @Test
    void largerThanAndReadableTest() throws IOException {
        DirectoryStream.Filter<Path> filter =
            AbstractFilter.largerThan(1200).and(AbstractFilter.readable);
        var expected = new HashSet<Path>();
        var result = new HashSet<Path>();

        expected.add(path.resolve("Task1Test.java"));
        expected.add(path.resolve("Task2Test.java"));
        expected.add(path.resolve("Task3Test.java"));

        try (var entries = Files.newDirectoryStream(path, filter)) {
            entries.forEach(result::add);
            assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Test
    void magicNumberTest() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.magicNumber(0x70);
        var expected = new HashSet<Path>();
        var result = new HashSet<Path>();

        Files.createFile(path.resolve("test.txt"));
        Files.write(path.resolve("test.txt"), Set.of("test"));

        expected.add(path.resolve("Task1Test.java"));
        expected.add(path.resolve("Task2Test.java"));
        expected.add(path.resolve("Task3Test.java"));
        expected.add(path.resolve("Task4Test.java"));
        expected.add(path.resolve("Task5Test.java"));
        expected.add(path.resolve("Task6Test.java"));

        try (var entries = Files.newDirectoryStream(path, filter)) {
            entries.forEach(result::add);
            assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Test
    void globTest() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.globMatches("*.txt");
        var expected = new HashSet<Path>();
        var result = new HashSet<Path>();

        Files.createFile(path.resolve("test.txt"));
        Files.write(path.resolve("test.txt"), Set.of("test"));

        expected.add(path.resolve("test.txt"));

        try (var entries = Files.newDirectoryStream(path, filter)) {
            entries.forEach(result::add);
            assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Test
    void regexContainsTest() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.regexContains("Task[1-3]Test.java");
        var expected = new HashSet<Path>();
        var result = new HashSet<Path>();

        Files.createFile(path.resolve("test.txt"));
        Files.write(path.resolve("test.txt"), Set.of("test"));

        expected.add(path.resolve("Task1Test.java"));
        expected.add(path.resolve("Task2Test.java"));
        expected.add(path.resolve("Task3Test.java"));

        try (var entries = Files.newDirectoryStream(path, filter)) {
            entries.forEach(result::add);
            assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
        }
    }
}
