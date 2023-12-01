package edu.hw8;

import edu.hw8.task1.MyClient;
import edu.hw8.task1.MyServer;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void oneClientTest() throws InterruptedException {
        var server = new MyServer(101, 6);
        var threadServer = new Thread(server::start);
        threadServer.start();
        Thread.sleep(1000);
        var client = new MyClient(101);

        assertThat(client.getQuote("глупый"))
            .isEqualTo("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма");
    }

    @Test
    void multiClientTest() throws InterruptedException {
        var server = new MyServer(100, 2);
        var threadServer = new Thread(server::start);
        threadServer.start();
        Thread.sleep(1000);
        var client1 = new MyClient(100);
        var client2 = new MyClient(100);
        var client3 = new MyClient(100);
        var client4 = new MyClient(100);
        var client5 = new MyClient(100);
        var listOfThread = List.of(
            new Thread(() -> client1.getQuote("глупый")),
            new Thread(() -> client2.getQuote("глупый")),
            new Thread(() -> client3.getQuote("глупый")),
            new Thread(() -> client4.getQuote("глупый"))
        );
        listOfThread.forEach(Thread::start);

        assertThat(client5.getQuote("глупый"))
            .isEqualTo("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма");
    }
}
