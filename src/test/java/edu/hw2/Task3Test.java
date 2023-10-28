package edu.hw2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.*;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    void goodFaultyConnectionManagerTest() {
        var manager = new FaultyConnectionManager();
        int maxAttempts = 5;

        var executor = new PopularCommandExecutor(manager, maxAttempts);

        assertThatCode(() -> executor.tryExecute("GoodCommand")).doesNotThrowAnyException();
    }

    @Test
    void badFaultyConnectionManagerTest() {
        var manager = new FaultyConnectionManager();
        int maxAttempts = 5;

        var executor = new PopularCommandExecutor(manager, maxAttempts);

        assertThatThrownBy(() -> executor.tryExecute("BadCommand")).isInstanceOf(ConnectionException.class);
    }

    @Test
    void goodDefaultConnectionManagerTest() {
        var manager = new DefaultConnectionManager(0.0);
        int maxAttempts = 1;

        var executor = new PopularCommandExecutor(manager, maxAttempts);

        assertThatCode(() -> executor.tryExecute("BadCommand")).doesNotThrowAnyException();
    }

    @Test
    void badDefaultConnectionManagerTest() {
        var manager = new DefaultConnectionManager(1.0);
        int maxAttempts = 1;

        var executor = new PopularCommandExecutor(manager, maxAttempts);

        assertThatThrownBy(() -> executor.tryExecute("BadCommand")).isInstanceOf(ConnectionException.class);
    }
}
