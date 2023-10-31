package edu.hw4;

import java.util.Objects;

public class ValidationError {
    private final String error;

    public ValidationError(String error) {
        this.error = error;
    }

    @Override public String toString() {
        return error;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValidationError that = (ValidationError) o;
        return Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error);
    }
}
