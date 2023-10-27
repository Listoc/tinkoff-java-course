package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyStockMarket implements StockMarket {
    private final Queue<Stock> stocksQueue = new PriorityQueue<>(Comparator.reverseOrder());

    @Override
    public void add(Stock stock) {
        stocksQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocksQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocksQueue.peek();
    }
}
