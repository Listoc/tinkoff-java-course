package edu.hw8.task1;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyClient {
    private final int port;

    public MyClient(int port) {
        this.port = port;
    }

    public String getQuote(String word) {
        try (
            var socket = new Socket("localhost", port);
            var inputStream = socket.getInputStream();
            var outputStream = socket.getOutputStream()
        ) {
            outputStream.write(word.getBytes(StandardCharsets.UTF_8));
            var bytes = inputStream.readAllBytes();

            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
