package edu.hw10.task2;

public interface Factorial {
    @Cache
    Long noPersistFactorial(Integer number);

    @Cache(persist = true)
    Long persistFactorial(Integer number);
}
