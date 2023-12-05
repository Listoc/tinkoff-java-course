package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class ClonerUtil {
    public static Path cloneFile(@NotNull Path path) throws IOException {
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path to directory");
        }

        if (!Files.exists(path)) {
            Files.createFile(path);
            return path;
        }

        var file = parseFile(path);
        int index = 2;
        var newFile = Path.of(file.prefix + " - копия" + file.postfix);

        while (Files.exists(newFile)) {
            newFile = Path.of(file.prefix + " - копия (" + index + ")" + file.postfix);
            index++;
        }

        Files.copy(path, newFile);

        return newFile;
    }

    private static MyFile parseFile(@NotNull Path path) {
        if (path.getFileName() == null) {
            throw new IllegalArgumentException("Can't get file name");
        }

        String postfix = "";
        var prefixBuilder = new StringBuilder();
        var fileNameString = path.getFileName().toString();
        var fileNameArray = fileNameString.toCharArray();

        for (var ch : fileNameArray) {
            if (ch == '.') {
                break;
            }

            prefixBuilder.append(ch);
        }

        if (prefixBuilder.length() != fileNameString.length()) {
            postfix = fileNameString.substring(prefixBuilder.length());
        }

        return new MyFile(prefixBuilder.toString(), postfix);
    }

    private ClonerUtil() {}

    private record MyFile(String prefix, String postfix) {}
}
