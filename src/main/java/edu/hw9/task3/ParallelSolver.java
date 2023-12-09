package edu.hw9.task3;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.solver.SearchNode;
import edu.project2.solver.Solver;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ParallelSolver implements Solver {
    private final static int THREAD_COUNT = 6;
    private final Set<SearchNode> visited = Collections.synchronizedSet(new HashSet<>());
    private final BlockingQueue<SearchNode> stack = new LinkedBlockingQueue<>();
    private SearchNode result;

    @Override
    public List<Cell> solve(Maze maze, int height1, int width1, int height2, int width2) {
        if (!isInputCorrect(maze, height1, width1, height2, width2)) {
            return null;
        }

        var root = new SearchNode(maze.getCell(height1, width1));
        var goal = maze.getCell(height2, width2);

        solve(maze, root, goal);

        if (result == null) {
            return null;
        }

        var list = new LinkedList<Cell>();

        for (SearchNode i = result; i != null; i = i.getParent()) {
            list.addFirst(i.getCell());
        }

        return list;
    }

    private void solve(Maze maze, SearchNode root, Cell goal) {
        stack.add(root);
        visited.add(root);
        var threadList = Stream.generate(() -> new Thread(parallelDFS(maze, goal))).limit(THREAD_COUNT).toList();
        threadList.forEach(Thread::start);
        try {
            for (var thread : threadList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Runnable parallelDFS(Maze maze, Cell goal) {
        return () -> {
            while (true) {
                if (result != null) {
                    break;
                }

                SearchNode currentNode;

                try {
                    currentNode = stack.poll(1, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (currentNode == null) {
                    break;
                }

                if (currentNode.isGoal(goal)) {
                    synchronized (this) {
                        result = currentNode;
                        break;
                    }
                }

                for (var neighbor : currentNode.findNeighbors(maze)) {
                    if (!visited.contains(neighbor)) {
                        stack.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        };
    }
}
