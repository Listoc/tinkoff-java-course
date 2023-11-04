package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DFSGenerator implements Generator {
    @Override
    public Maze generate(int height, int width) {
        var maze = new Maze(height, width);
        var random = new Random();
        int randomIndex;
        var stack = new ArrayDeque<Cell>();
        var visited = new HashSet<Cell>();
        Cell currentCell = maze.getCell(0, 0);
        List<Cell> neighbors;
        Cell currentNeighbor;

        setMazeForDFS(maze);
        visited.add(currentCell);

        while (currentCell != null) {
            neighbors = getNeighbors(maze, currentCell, visited);

            if (neighbors.isEmpty()) {
                currentCell = stack.pollFirst();
                continue;
            }

            randomIndex = random.nextInt(neighbors.size());
            currentNeighbor = neighbors.get(randomIndex);

            breakWall(maze, currentCell, currentNeighbor);

            stack.addFirst(currentCell);
            visited.add(currentNeighbor);
            currentCell = currentNeighbor;
        }

        return maze;
    }

    private void breakWall(Maze maze, Cell cell1, Cell cell2) {
        int wallHeight;
        int wallWidth;

        if (cell1.getHeight() > cell2.getHeight()) {
            wallHeight = cell1.getHeight() - 1;
        } else if (cell1.getHeight() < cell2.getHeight()) {
            wallHeight = cell1.getHeight() + 1;
        } else {
            wallHeight = cell1.getHeight();
        }

        if (cell1.getWidth() > cell2.getWidth()) {
            wallWidth = cell1.getWidth() - 1;
        } else if (cell1.getWidth() < cell2.getWidth()) {
            wallWidth = cell1.getWidth() + 1;
        } else {
            wallWidth = cell1.getWidth();
        }

        maze.getCell(wallHeight, wallWidth).setType(Cell.Type.Passage);
    }

    private List<Cell> getNeighbors(Maze maze, Cell cell, Set<Cell> visited) {
        var list = new ArrayList<Cell>();
        Cell currentCell;
        if (cell.getHeight() > 1) {
            currentCell = maze.getCell(cell.getHeight() - 2, cell.getWidth());
            if (!visited.contains(currentCell) && currentCell.getType() != Cell.Type.Wall) {
                list.add(currentCell);
            }
        }

        if (cell.getHeight() < maze.getHeight() - 2) {
            currentCell = maze.getCell(cell.getHeight() + 2, cell.getWidth());
            if (!visited.contains(currentCell) && currentCell.getType() != Cell.Type.Wall) {
                list.add(currentCell);
            }
        }

        if (cell.getWidth() > 1) {
            currentCell = maze.getCell(cell.getHeight(), cell.getWidth() - 2);
            if (!visited.contains(currentCell) && currentCell.getType() != Cell.Type.Wall) {
                list.add(currentCell);
            }
        }

        if (cell.getWidth() < maze.getWidth() - 2) {
            currentCell = maze.getCell(cell.getHeight(), cell.getWidth() + 2);
            if (!visited.contains(currentCell) && currentCell.getType() != Cell.Type.Wall) {
                list.add(currentCell);
            }
        }

        return list;
    }

    private void setMazeForDFS(Maze maze) {
        var mazeArray = maze.getMazeArray();

        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (i % 2 == 0 && j % 2 == 0) {
                    mazeArray[i][j].setType(Cell.Type.Passage);
                } else {
                    mazeArray[i][j].setType(Cell.Type.Wall);
                }
            }
        }
    }
}
