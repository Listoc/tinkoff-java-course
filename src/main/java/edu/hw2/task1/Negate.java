package edu.hw2.task1;

public record Negate(Expr expression) implements Expr {
    public Negate {
        if (expression == null) {
            throw new IllegalArgumentException("Null input");
        }
    }

    @Override
    public double evaluate() {
        return -1 * expression.evaluate();
    }
}
