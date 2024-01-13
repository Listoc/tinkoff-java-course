package edu.hw9;

import edu.hw9.task3.ParallelSolver;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private static final Maze properMaze;
    private static final Maze badMaze;
    private static final List<Cell> expected;

    static {
        properMaze = new Maze(5, 5);
        badMaze = new Maze(5, 5);
        expected = new ArrayList<>();
            /*
            _________________
            |   ###         |
            |   ###   ###   |
            |         ###   |
            |######   ######|
            |               |
            ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
            */
        properMaze.setAllPassages();
        properMaze.getCell(0, 1).setType(Cell.Type.Wall);
        properMaze.getCell(1, 1).setType(Cell.Type.Wall);
        properMaze.getCell(3, 0).setType(Cell.Type.Wall);
        properMaze.getCell(3, 1).setType(Cell.Type.Wall);
        properMaze.getCell(3, 3).setType(Cell.Type.Wall);
        properMaze.getCell(3, 4).setType(Cell.Type.Wall);
        properMaze.getCell(2, 3).setType(Cell.Type.Wall);
        properMaze.getCell(1, 3).setType(Cell.Type.Wall);

            /*
            _________________
            |   ###         |
            |   #########   |
            |         ###   |
            |######   ######|
            |               |
            ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
            */
        badMaze.setAllPassages();
        badMaze.getCell(0, 1).setType(Cell.Type.Wall);
        badMaze.getCell(1, 1).setType(Cell.Type.Wall);
        badMaze.getCell(3, 0).setType(Cell.Type.Wall);
        badMaze.getCell(3, 1).setType(Cell.Type.Wall);
        badMaze.getCell(3, 3).setType(Cell.Type.Wall);
        badMaze.getCell(3, 4).setType(Cell.Type.Wall);
        badMaze.getCell(2, 3).setType(Cell.Type.Wall);
        badMaze.getCell(1, 3).setType(Cell.Type.Wall);
        badMaze.getCell(1, 2).setType(Cell.Type.Wall);

            /*
            _________________
            | . ### .  .  . |
            | . ### . ### . |
            | .  .  . ### . |
            |######   ######|
            |               |
            ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
            */
        expected.add(properMaze.getCell(0, 0));
        expected.add(properMaze.getCell(1, 0));
        expected.add(properMaze.getCell(2, 0));
        expected.add(properMaze.getCell(2, 1));
        expected.add(properMaze.getCell(2, 2));
        expected.add(properMaze.getCell(1, 2));
        expected.add(properMaze.getCell(0, 2));
        expected.add(properMaze.getCell(0, 3));
        expected.add(properMaze.getCell(0, 4));
        expected.add(properMaze.getCell(1, 4));
        expected.add(properMaze.getCell(2, 4));
    }

    @Test
    void properMaze() {
        assertThat(new ParallelSolver().solve(properMaze, 0, 0, 2, 4)).containsExactlyElementsOf(expected);
    }

    @Test
    void wrongMaze() {
        assertThat(new ParallelSolver().solve(badMaze, 0, 0, 2, 4)).isNull();
    }

    @Test
    void wrongCells() {
        assertThat(new ParallelSolver().solve(properMaze, 1, 1, 2, 4)).isNull();
    }

    @Test
    void wrongInput() {
        assertThat(new ParallelSolver().solve(properMaze, -1, -1, -1, -1)).isNull();
    }
}
