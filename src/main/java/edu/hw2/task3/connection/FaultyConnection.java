package edu.hw2.task3.connection;

import edu.hw2.task3.exception.ConnectionException;

public class FaultyConnection implements Connection {
    @Override
    public void execute(String command) {
        if (command.equals("BadCommand")) {
            throw new ConnectionException("Can't execute this command");
        }
    }

    @Override
    public void close() throws Exception { }
}
