package edu.hw2.task1;

public record Addition(Expr firstExpression, Expr secondExpression) implements Expr {
    public Addition {
        if (firstExpression == null || secondExpression == null) {
            throw new IllegalArgumentException("Null input");
        }
    }

    @Override
    public double evaluate() {
        return firstExpression.evaluate() + secondExpression.evaluate();
    }
}
