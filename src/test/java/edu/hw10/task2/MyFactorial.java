package edu.hw10.task2;

public class MyFactorial implements Factorial {

    @Override
    public Long noPersistFactorial(Integer number) {
        return factorial(number);
    }

    @Override
    public Long persistFactorial(Integer number) {
        return factorial(number);
    }

    private Long factorial(Integer number) {
        if (number <= 0) {
            return 1L;
        }

        return number * factorial(number - 1);
    }
}
