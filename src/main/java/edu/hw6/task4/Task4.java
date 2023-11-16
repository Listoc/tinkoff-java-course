package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    public static void print(Path path) throws IOException {
        if (path == null || Files.isDirectory(path)) {
            throw new IllegalArgumentException();
        }

        try (
            var outputStream = Files.newOutputStream(path);
            var checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
            var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            var printWriter = new PrintWriter(outputStreamWriter);
            ) {
            printWriter.print("Programming is learned by writing programs. ― Brian Kernighan");
        }
    }

    private Task4() {}
}
