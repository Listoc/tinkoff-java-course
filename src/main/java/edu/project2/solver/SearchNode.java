package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchNode {
    private final Cell cell;
    private final SearchNode parent;
    private final int depth;
    private int priority;

    public SearchNode(Cell cell, SearchNode parent, int depth) {
        if (cell == null) {
            throw new IllegalArgumentException("Null cell");
        }

        this.cell = cell;
        this.parent = parent;
        this.depth = depth;
    }

    public SearchNode(Cell cell) {
        this(cell, null, 0);
    }

    public List<SearchNode> findNeighbors(Maze maze) {
        if (maze == null) {
            return null;
        }

        var neighbors = new ArrayList<SearchNode>();
        Cell currentNeighbor;

        if (cell.getHeight() > 0) {
            currentNeighbor = maze.getCell(cell.getHeight() - 1, cell.getWidth());
            if (currentNeighbor.getType() == Cell.Type.Passage) {
                neighbors.add(new SearchNode(currentNeighbor, this, depth + 1));
            }
        }

        if (cell.getHeight() < maze.getHeight() - 1) {
            currentNeighbor = maze.getCell(cell.getHeight() + 1, cell.getWidth());
            if (currentNeighbor.getType() == Cell.Type.Passage) {
                neighbors.add(new SearchNode(currentNeighbor, this, depth + 1));
            }
        }

        if (cell.getWidth() > 0) {
            currentNeighbor = maze.getCell(cell.getHeight(), cell.getWidth() - 1);
            if (currentNeighbor.getType() == Cell.Type.Passage) {
                neighbors.add(new SearchNode(currentNeighbor, this, depth + 1));
            }
        }

        if (cell.getWidth() < maze.getWidth() - 1) {
            currentNeighbor = maze.getCell(cell.getHeight(), cell.getWidth() + 1);
            if (currentNeighbor.getType() == Cell.Type.Passage) {
                neighbors.add(new SearchNode(currentNeighbor, this, depth + 1));
            }
        }

        return neighbors;
    }

    public Cell getCell() {
        return cell;
    }

    public SearchNode getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isGoal(Cell goal) {
        return cell.equals(goal);
    }

    private int getManhattanDistance(Cell goal) {
        int heightDistance = Math.abs(cell.getHeight() - goal.getHeight());
        int widthDistance = Math.abs(cell.getWidth() - goal.getWidth());

        return heightDistance + widthDistance;
    }

    public int setPriorityAStar(Cell goal) {
        priority = depth + getManhattanDistance(goal);
        return priority;
    }

    public int setPriorityGreedy(Cell goal) {
        priority = getManhattanDistance(goal);
        return priority;
    }

    @Override public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        SearchNode that = (SearchNode) other;
        return Objects.equals(cell, that.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell);
    }
}
