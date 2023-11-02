package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze, int height1, int width1, int height2, int width2);

    default boolean isInputCorrect(Maze maze, int height1, int width1, int height2, int width2) {
        if (maze == null) {
            return false;
        }

        if (height1 > maze.getHeight() - 1
            || height2 > maze.getHeight() - 1
            || width1 > maze.getWidth() - 1
            || width2 > maze.getWidth() - 1
            || maze.getCell(height1, width1).getType() == Cell.Type.Wall
            || maze.getCell(height2, width2).getType() == Cell.Type.Wall) {
            return false;
        }

        return true;
    }
}
