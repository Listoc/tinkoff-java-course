package edu.project2;

import edu.project2.Printer.PrettyPrinter;
import edu.project2.generator.DFSGenerator;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.solver.ASolver;
import edu.project2.solver.BFSSolver;
import edu.project2.solver.DFSSolver;
import edu.project2.solver.GreedySolver;
import edu.project2.solver.Solver;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Project2Test {
    @Nested
    public class SolverTest {
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

        static Arguments[] solvers() {
            return new Arguments[] {
                Arguments.of(new DFSSolver()),
                Arguments.of(new ASolver()),
                Arguments.of(new GreedySolver()),
                Arguments.of(new BFSSolver())
            };
        }

        @ParameterizedTest
        @MethodSource("solvers")
        void properMaze(Solver solver) {
            assertThat(solver.solve(properMaze, 0, 0, 2, 4)).isEqualTo(expected);
        }

        @ParameterizedTest
        @MethodSource("solvers")
        void wrongMaze(Solver solver) {
            assertThat(solver.solve(badMaze, 0, 0, 2, 4)).isNull();
        }

        @ParameterizedTest
        @MethodSource("solvers")
        void wrongCells(Solver solver) {
            assertThat(solver.solve(properMaze, 1, 1, 2, 4)).isNull();
        }

        @ParameterizedTest
        @MethodSource("solvers")
        void wrongInput(Solver solver) {
            assertThat(solver.solve(properMaze, -1, -1, -1, -1)).isNull();
        }
    }

    @Nested
    public class MazeTest {
        @Test
        void wrongInput() {
            assertThatThrownBy(() -> new Maze(-1, -1)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    public class CellTest {
        @Test
        void wrongInput() {
            assertThatThrownBy(() -> new Cell(-1, -1, null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    public class GeneratorTest {
        @Test
        void wrongInput() {
            assertThatThrownBy(() -> new DFSGenerator().generate(-1, -1)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    public class PrinterTest {
        @Test
        void wrongInput() {
            assertThatThrownBy(() -> new PrettyPrinter().printMaze(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
