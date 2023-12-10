package edu.project4.model;

public class Pixel {
    public static final int MIN_COLOR = 0;
    public static final int MAX_COLOR = 255;
    private int r = MIN_COLOR;
    private int g = MIN_COLOR;
    private int b = MIN_COLOR;
    private int hitCount = 0;
    private double normal = 0.0;

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public void setR(int r) {
        if (r > MAX_COLOR || r < 0) {
            this.r = MAX_COLOR;
        } else {
            this.r = r;
        }
    }

    public void setG(int g) {
        if (g > MAX_COLOR || g < 0) {
            this.g = MAX_COLOR;
        } else {
            this.g = g;
        }
    }

    public void setB(int b) {
        if (b > MAX_COLOR || b < 0) {
            this.b = MAX_COLOR;
        } else {
            this.b = b;
        }
    }

    public void incrementHitCount() {
        hitCount++;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getHitCount() {
        return hitCount;
    }
}
