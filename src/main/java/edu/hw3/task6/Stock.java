package edu.hw3.task6;

import org.jetbrains.annotations.NotNull;

public record Stock(int price) implements Comparable<Stock> {
    public Stock {
        if (price < 0) {
            throw new IllegalArgumentException("Negative price");
        }
    }

    @Override
    public int compareTo(@NotNull Stock other) {
        return Integer.compare(this.price, other.price);
    }
}
