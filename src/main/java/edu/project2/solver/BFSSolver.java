package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BFSSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze, int height1, int width1, int height2, int width2) {
        if (!isInputCorrect(maze, height1, width1, height2, width2)) {
            return null;
        }

        Cell root = maze.getCell(height1, width1);
        Cell goal = maze.getCell(height2, width2);

        SearchNode result = solve(maze, root, goal);

        if (result == null) {
            return null;
        }

        var list = new LinkedList<Cell>();

        for (SearchNode i = result; i != null; i = i.getParent()) {
            list.addFirst(i.getCell());
        }

        return list;
    }

    private SearchNode solve(Maze maze, Cell root, Cell goal) {
        var queue = new ArrayDeque<SearchNode>();
        var visited = new HashSet<SearchNode>();
        var currentNode = new SearchNode(root);
        List<SearchNode> neighbors;

        queue.addLast(currentNode);
        visited.add(currentNode);

        while (!queue.isEmpty()) {
            currentNode = queue.pollFirst();

            if (currentNode.isGoal(goal)) {
                return currentNode;
            }

            neighbors = currentNode.findNeighbors(maze);
            for (var neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.addLast(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null;
    }

    private boolean isInputCorrect(Maze maze, int height1, int width1, int height2, int width2) {
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
