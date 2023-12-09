package edu.hw9;

import edu.hw9.task2.DirectoriesSearch;
import edu.hw9.task2.FilesSearch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static Path baseDir;
    private static Path dir1;
    private static Path dir2;
    private static Path dir3;
    private static Path dir4;
    private static Path dir5;
    private static List<Path> files;

    @BeforeAll
    static void prepare() throws IOException {
        baseDir = Files.createTempDirectory("baseDir");
        dir1 = Files.createTempDirectory(baseDir,"dir1");
        dir2 = Files.createTempDirectory(baseDir,"dir2");
        dir3 = Files.createTempDirectory(dir1,"dir1");
        dir4 = Files.createTempDirectory(dir3,"dir2");
        dir5 = Files.createTempDirectory(dir3,"dir5");
        files = new LinkedList<>();

        for (int i = 0; i < 1001; ++i) {
            files.add(Files.createTempFile(dir1, null, ".txt"));
            files.add(Files.createTempFile(dir4, null, ".txt"));
        }

        for (int i = 0; i < 100; ++i) {
            files.add(Files.createTempFile(dir2, null, ".txt"));
            files.add(Files.createTempFile(dir3, null, ".txt"));
        }
    }

    @AfterAll
    static void clear() throws IOException {
        for (var file : files) {
            Files.delete(file);
        }

        Files.deleteIfExists(dir5);
        Files.deleteIfExists(dir4);
        Files.deleteIfExists(dir3);
        Files.deleteIfExists(dir2);
        Files.deleteIfExists(dir1);
        Files.deleteIfExists(baseDir);
    }

    @Nested
    class DirectoriesTest {
        @Test
        void normalWorkTest() {
            assertThat(
                DirectoriesSearch.findDirectoriesWith1000Files(baseDir))
                .containsExactlyInAnyOrderElementsOf(List.of(dir1, dir4));
        }
    }

    @Nested
    class FilesTest {
        @Test
        void directoriesSearchTest() {
            assertThat(
                FilesSearch.findFilesByPredicate(baseDir, Files::isDirectory))
                .containsExactlyInAnyOrderElementsOf(List.of(dir1, dir2, dir3, dir4, dir5));
        }

        @Test
        void javaFilesSearchTest() throws IOException {
            var file1 = Files.createTempFile(dir3, null, ".java");
            var file2 = Files.createTempFile(baseDir, null, ".java");

            assertThat(
                FilesSearch.findFilesByPredicate(baseDir, path -> path.getFileName().toString().endsWith(".java")))
                .containsExactlyInAnyOrderElementsOf(List.of(file1, file2));

            Files.deleteIfExists(file1);
            Files.deleteIfExists(file2);
        }
    }
}
