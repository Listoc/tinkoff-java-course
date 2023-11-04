package edu.project2.model;

import java.util.Objects;

public class Cell {
    private final int height;
    private final int width;
    private Type type;

    public Cell(int height, int width, Type type) {
        if (height < 0 || width < 0 || type == null) {
            throw new IllegalArgumentException("Wrong arguments!");
        }
        this.height = height;
        this.width = width;
        this.type = type;
    }

    public enum Type {
        Wall,
        Passage
    }

    public void setType(Type type) {
        if (type != null) {
            this.type = type;
        }
    }

    @Override public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Cell cell = (Cell) other;
        return height == cell.height && width == cell.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    @Override public String toString() {
        return "Cell{"
            + "height=" + height
            + ", width=" + width
            + ", type=" + type
            + '}';
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Type getType() {
        return type;
    }
}
