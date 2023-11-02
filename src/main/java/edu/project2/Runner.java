package edu.project2;

import edu.project2.Printer.Printer;
import edu.project2.generator.Generator;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.solver.Solver;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Generator generator;
    private final Solver solver;
    private final Printer printer;
    private final static int MIN_SIZE = 3;

    public Runner(Generator generator, Solver solver, Printer printer) {
        this.generator = generator;
        this.solver = solver;
        this.printer = printer;
    }

    public void run() {
        var in = new Scanner(System.in);
        int height;
        int width;
        int heightCell1;
        int widthCell1;
        int heightCell2;
        int widthCell2;
        List<Cell> path;
        Maze maze;

        do {
            LOGGER.info("Введите высоту лабиринта (не меньше 3)");
            height = in.nextInt();
            LOGGER.info("Введите ширину лабиринта (не меньше 3)");
            width = in.nextInt();
        } while (height < MIN_SIZE || width < MIN_SIZE);

        maze = generator.generate(height, width);

        printer.printMaze(maze);

        LOGGER.info("Введите координаты первой точки (высота и ширина через пробел)");
        heightCell1 = in.nextInt();
        widthCell1 = in.nextInt();

        LOGGER.info("Введите координаты второй точки (высота и ширина через пробел)");
        heightCell2 = in.nextInt();
        widthCell2 = in.nextInt();

        path = solver.solve(maze, heightCell1, widthCell1, heightCell2, widthCell2);

        if (path == null) {
            LOGGER.info("Не пути между этими клетками!");
        } else {
            printer.printMaze(maze, path);
        }
    }
}
