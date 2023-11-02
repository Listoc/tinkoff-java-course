package edu.project2.Printer;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public class PrettyPrinter implements Printer {
    private static final int CELL_LENGTH = 3;

    public void printMaze(Maze maze) {
        printMazeShared(maze, null);
    }

    public void printMaze(Maze maze, List<Cell> path) {
        printMazeShared(maze, path);
    }

    private void printMazeShared(Maze maze, List<Cell> path) {
        if (maze == null) {
            throw new IllegalArgumentException("Null maze");
        }
        System.out.println(getTopBorder(maze.getWidth()));

        for (int i = 0; i < maze.getHeight(); ++i) {
            System.out.print("|");
            for (int j = 0; j < maze.getWidth(); ++j) {
                System.out.print(cellToString(maze.getCell(i, j), path));
            }
            System.out.println("|");
        }

        System.out.println(getBottomBorder(maze.getWidth()));
    }

    private String getBottomBorder(int width) {
        return "‾" + "‾".repeat(width * CELL_LENGTH) + "‾";
    }

    private String getTopBorder(int width) {
        return "_" + "_".repeat(width * CELL_LENGTH) + "_";
    }

    private String cellToString(Cell cell, List<Cell> path) {
        if (path != null && path.contains(cell)) {
            return " . ";
        } else if (cell.getType() == Cell.Type.Wall) {
            return "###";
        } else {
            return "   ";
        }
    }
}
