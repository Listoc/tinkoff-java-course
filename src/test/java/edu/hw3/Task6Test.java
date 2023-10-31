package edu.hw3;

import edu.hw3.task6.MyStockMarket;
import edu.hw3.task6.Stock;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task6Test {
    @Test
    void addTest() {
        var market = new MyStockMarket();

        market.add(new Stock(50));

        assertThat(market.mostValuableStock().price()).isEqualTo(50);
    }

    @Test
    void removeTest() {
        var market = new MyStockMarket();

        market.add(new Stock(50));
        market.remove(new Stock(50));

        assertThat(market.mostValuableStock()).isNull();
    }

    @Test
    void mostValuableStockTest() {
        var market = new MyStockMarket();

        market.add(new Stock(50));
        market.add(new Stock(25));
        market.add(new Stock(75));
        market.add(new Stock(49));

        assertThat(market.mostValuableStock().price()).isEqualTo(75);
        assertThat(market.mostValuableStock().price()).isEqualTo(75);
    }

    @Test
    void negativeStockPrice() {
        assertThatThrownBy(() -> new Stock(-50)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getFromEmptyStockMarket() {
        assertThat(new MyStockMarket().mostValuableStock()).isNull();
    }

    @Test
    void addNullToStockMarket() {
        var market = new MyStockMarket();
        assertThatThrownBy(() -> market.add(null)).isInstanceOf(NullPointerException.class);
    }
}
