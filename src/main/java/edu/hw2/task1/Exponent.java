package edu.hw2.task1;

public record Exponent(Expr firstExpression, int power) implements Expr {
    public Exponent {
        if (firstExpression == null) {
            throw new IllegalArgumentException("Null input");
        }
    }

    @Override
    public double evaluate() {
        return Math.pow(firstExpression.evaluate(), power);
    }
}
