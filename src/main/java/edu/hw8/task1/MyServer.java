package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyServer {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_PORT = 65536;
    private static final int MAX_LENGTH_OF_WORD = 256;
    private final List<String> quotes;
    private final int port;
    private boolean isWorking;
    private final int numberOfThreads;

    public MyServer(List<String> quotes, int port, int numberOfThreads) {
        if (quotes == null || port < 0 || port > MAX_PORT || numberOfThreads < 1) {
            throw new IllegalArgumentException("Wrong parameters!");
        }
        this.quotes = quotes;
        this.port = port;
        this.numberOfThreads = numberOfThreads;
        this.isWorking = true;
    }

    public MyServer(int port, int numberOfThreads) {
        this(new LinkedList<>(), port, numberOfThreads);

        quotes.add("Не переходи на личности там, где их нет.");
        quotes.add("Если test твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.");
        quotes.add("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма");
        quotes.add("Чем ниже интеллект, тем громче оскорбления.");
    }

    public void addQuote(String quote) {
        quotes.add(quote);
    }

    public void start() {
        try (
            var socket = new ServerSocket(port);
            var pool = Executors.newFixedThreadPool(numberOfThreads);
        ) {
            while (isWorking) {
                var client = socket.accept();
                LOGGER.info("Got connection");
                pool.submit(processRequest(client));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        isWorking = false;
    }

    private Runnable processRequest(Socket socket) {
        return () -> {
            try (
                var inputStream = socket.getInputStream();
                var outputStream = socket.getOutputStream()
            ) {
                boolean sent = false;
                byte[] b = new byte[MAX_LENGTH_OF_WORD];
                int len;
                len = inputStream.read(b);
                String input = new String(Arrays.copyOf(b, len), StandardCharsets.UTF_8);
                LOGGER.info(Thread.currentThread().getName() + " Read word: " + input);

                for (var quote : quotes) {
                    if (quote.toLowerCase().contains(input)) {
                        outputStream.write(quote.getBytes(StandardCharsets.UTF_8));
                        sent = true;
                        LOGGER.info(Thread.currentThread().getName() + " Sent quote: " + input);
                        break;
                    }
                }

                if (!sent) {
                    outputStream.write("No quote with that word".getBytes(StandardCharsets.UTF_8));
                    LOGGER.info(Thread.currentThread().getName() + " Did not send quote: " + input);
                }
                LOGGER.info(Thread.currentThread().getName() + " Connection ended");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
