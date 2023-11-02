package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze, int y1, int x1, int y2, int x2);
}
