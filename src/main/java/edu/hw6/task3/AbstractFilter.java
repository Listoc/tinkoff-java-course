package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings({"ConstantName", "MagicNumber"})
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    AbstractFilter readable = Files::isReadable;
    AbstractFilter writable = Files::isWritable;
    AbstractFilter hidden = Files::isHidden;
    AbstractFilter executable = Files::isExecutable;
    AbstractFilter regular = Files::isRegularFile;

    static AbstractFilter largerThan(long size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        return (Path path) -> Files.size(path) > size;
    }

    static AbstractFilter globMatches(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        return (Path path) -> path.getFileName().toString().endsWith(str.substring(1));
    }

    static AbstractFilter regexContains(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        return (Path path) -> path.getFileName().toString().matches(str);
    }

    static AbstractFilter magicNumber(int... bytes) {
        return (Path path) -> {
            try {
                var firstByte = Integer.toHexString(Files.readAllBytes(path)[0] & 0xff);
                for (var el : bytes) {
                    if (Integer.toHexString(el & 0xff).equals(firstByte)) {
                        return true;
                    }
                }
            } catch (Exception ignored) { }
            return false;
        };
    }

    default AbstractFilter and(AbstractFilter other) {
        return entry -> accept(entry) && other.accept(entry);
    }
}
