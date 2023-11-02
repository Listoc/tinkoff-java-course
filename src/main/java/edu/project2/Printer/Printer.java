package edu.project2.Printer;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Printer {
    void printMaze(Maze maze);

    void printMaze(Maze maze, List<Cell> path);
}
