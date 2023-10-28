package edu.hw2.task3.exception;

public class ConnectionException extends RuntimeException {
    private final Exception cause;

    public ConnectionException(String info) {
        super(info);
        this.cause = null;
    }

    public ConnectionException(String info, Exception cause) {
        super(info);
        this.cause = cause;
    }
}
