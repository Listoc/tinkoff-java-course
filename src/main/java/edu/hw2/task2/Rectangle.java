package edu.hw2.task2;

public class Rectangle {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative sizes");
        }
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}
