package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import org.jetbrains.annotations.NotNull;

public class DirectoriesSearch {
    public static List<Path> findDirectoriesWith1000Files(@NotNull Path startDirectory) {
        if (!Files.isDirectory(startDirectory)) {
            throw new IllegalArgumentException("Pass directory path!");
        }

        return new DirectoriesSearchTask(startDirectory).invoke();
    }

    private DirectoriesSearch() {}

    private static class DirectoriesSearchTask extends RecursiveTask<List<Path>> {
        private final Path currentPath;
        private final static int COUNT_OF_FILES = 1000;

        DirectoriesSearchTask(@NotNull Path currentPath) {
            this.currentPath = currentPath;
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

                if (childrenList.size() > COUNT_OF_FILES) {
                    resultList.add(currentPath);
                }

                childrenList = childrenList.stream().filter(Files::isDirectory).toList();

                var forkList = new LinkedList<ForkJoinTask<List<Path>>>();

                for (var path : childrenList) {
                    forkList.add(new DirectoriesSearchTask(path).fork());
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
