package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

public class FilesSearch {
    public static List<Path> findFilesByPredicate(@NotNull Path startDirectory, @NotNull Predicate<Path> predicate) {
        if (!Files.isDirectory(startDirectory)) {
            throw new IllegalArgumentException("Pass directory path!");
        }

        return new FilesSearchTask(startDirectory, predicate).invoke();
    }

    private FilesSearch() {}

    private static class FilesSearchTask extends RecursiveTask<List<Path>> {
        private final Path currentPath;
        private final Predicate<Path> predicate;

        FilesSearchTask(@NotNull Path currentPath, @NotNull Predicate<Path> predicate) {
            this.currentPath = currentPath;
            this.predicate = predicate;
        }

        @Override
        protected List<Path> compute() {
            List<Path> childrenList;
            List<Path> resultList = new LinkedList<>();

            try (var stream = Files.list(currentPath)) {
                childrenList = stream.toList();

                if (childrenList.isEmpty()) {
                    return new LinkedList<>();
                }

                var forkList = new LinkedList<ForkJoinTask<List<Path>>>();

                for (var path : childrenList) {
                    if (Files.isDirectory(path)) {
                        forkList.add(new FilesSearchTask(path, predicate).fork());
                    }

                    if (predicate.test(path)) {
                        resultList.add(path);
                    }
                }

                for (var fork : forkList) {
                    resultList.addAll(fork.join());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return resultList;
        }
    }
}
