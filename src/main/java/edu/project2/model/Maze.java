package edu.project2.model;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] mazeArray;

    public Maze(int height, int width) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Wrong size!");
        }
        this.height = height;
        this.width = width;
        this.mazeArray = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                mazeArray[i][j] = new Cell(i, j, Cell.Type.Wall);
            }
        }
    }

    public void setAllWalls() {
        for (var arr : mazeArray) {
            for (var cell : arr) {
                cell.setType(Cell.Type.Wall);
            }
        }
    }

    public void setAllPassages() {
        for (var arr : mazeArray) {
            for (var cell : arr) {
                cell.setType(Cell.Type.Passage);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getMazeArray() {
        return mazeArray;
    }

    public Cell getCell(int height, int width) {
        if (height < 0 || width < 0 || height > this.height - 1 || width > this.width - 1) {
            return null;
        }

        return mazeArray[height][width];
    }
}
