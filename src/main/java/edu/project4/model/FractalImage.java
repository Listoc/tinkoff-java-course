package edu.project4.model;

public class FractalImage {
    private final int width;
    private final int height;
    private final Pixel[][] data;

    public FractalImage(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Width and height must be greater than 0");
        }

        this.width = width;
        this.height = height;
        this.data = new Pixel[width][height];

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                data[i][j] = new Pixel();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel[][] getData() {
        return data;
    }
}
