package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings({"ConstantName", "MagicNumber"})
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    String NULL_MESSAGE = "Null string";
    AbstractFilter readable = Files::isReadable;
    AbstractFilter writable = Files::isWritable;
    AbstractFilter hidden = Files::isHidden;
    AbstractFilter executable = Files::isExecutable;
    AbstractFilter regular = Files::isRegularFile;

    static AbstractFilter largerThan(long size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative size");
        }
        return (Path path) -> Files.size(path) > size;
    }

    static AbstractFilter globMatches(@NotNull String str) {
        return (Path path) -> {
            var fileName = path.getFileName();

            if (fileName == null) {
                return false;
            }

            return fileName.toString().endsWith(str.substring(1));
        };
    }

    static AbstractFilter regexContains(@NotNull String str) {
        return (Path path) -> {
            var fileName = path.getFileName();

            if (fileName == null) {
                return false;
            }

            return fileName.toString().matches(str);
        };
    }

    static AbstractFilter magicNumber(int... bytes) {
        return (Path path) -> {
            if (Files.isDirectory(path)) {
                return false;
            }

            int firstByte;

            try (var reader = Files.newBufferedReader(path)) {
                firstByte = reader.read();
            }

            if (firstByte == -1) {
                return false;
            }

            for (var el : bytes) {
                if (el == firstByte) {
                    return true;
                }
            }

            return false;
        };
    }

    default AbstractFilter and(@NotNull AbstractFilter other) {
        return entry -> accept(entry) && other.accept(entry);
    }
}
