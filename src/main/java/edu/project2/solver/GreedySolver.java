package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedySolver implements Solver {
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
        var queue = new PriorityQueue<SearchNode>(Comparator.comparingInt(SearchNode::getPriority));
        var visited = new HashSet<SearchNode>();

        var currentNode = new SearchNode(root);
        List<SearchNode> neighbors;

        currentNode.setPriorityGreedy(goal);
        queue.add(currentNode);
        visited.add(currentNode);

        while (!queue.isEmpty()) {
            currentNode = queue.poll();

            if (currentNode.isGoal(goal)) {
                return currentNode;
            }

            neighbors = currentNode.findNeighbors(maze);
            for (var neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    neighbor.setPriorityGreedy(goal);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null;
    }
}
