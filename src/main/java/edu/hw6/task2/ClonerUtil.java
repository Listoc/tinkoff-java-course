package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClonerUtil {
    public static Path cloneFile(Path path) throws IOException {
        if (path == null || Files.isDirectory(path)) {
            throw new IllegalArgumentException();
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

    private static MyFile parseFile(Path path) {
        var split = path.getFileName().toString().split("\\.");
        String ending = "";
        StringBuilder builder = new StringBuilder();
        String fileName;

        if (split.length != 1) {
            ending = "." + split[split.length - 1];

            for (int i = 0; i < split.length - 1; ++i) {
                builder.append(split[i]);
                if (i != split.length - 2) {
                    builder.append(".");
                }
            }

            fileName = builder.toString();
        } else {
            fileName = split[0];
        }

        return new MyFile(fileName, ending);
    }

    private ClonerUtil() {}

    private record MyFile(String prefix, String postfix) {}
}
