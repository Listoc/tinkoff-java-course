package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.ConnectionManager;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void tryExecute(String command) throws Exception {
        Connection connection = manager.getConnection();
        for (int i = 0; i < maxAttempts; ++i) {
            try {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (i == maxAttempts - 1) {
                    throw new ConnectionException("Can't execute command", e);
                }
            }
        }
        connection.close();
    }
}
