package edu.project4;

import edu.project4.model.Rectangle;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.Transformation;
import edu.project4.util.CorrectionUtil;
import edu.project4.util.ImageUtil;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Runner {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Renderer renderer;

    public Runner(Renderer renderer) {
        this.renderer = renderer;
    }

    public void run() {
        var in = new Scanner(System.in);
        LOGGER.info("###Start###");
        LOGGER.info("Enter samples count: ");
        int samplesCount = in.nextInt();

        LOGGER.info("Enter iterations per sample count: ");
        int iterationsPerSample = in.nextInt();

        LOGGER.info("Enter affins count: ");
        int affinsCount = in.nextInt();

        LOGGER.info("Enter image width: ");
        int width = in.nextInt();

        LOGGER.info("Enter image height: ");
        int height = in.nextInt();

        LOGGER.info("Enter symmetry: ");
        int symmetry = in.nextInt();

        var transformations = new ArrayList<Transformation>();
        int choice = 1;
        do {
            LOGGER.info("Choose transformation:");
            LOGGER.info("0 - exit");
            LOGGER.info("1 - disc");
            LOGGER.info("2 - heart");
            LOGGER.info("3 - sinus");
            LOGGER.info("4 - spherical");
            LOGGER.info("5 - tan");
            LOGGER.info("6 - fish eye");
            LOGGER.info("7 - linear");
            LOGGER.info("Your choice: ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    transformations.add(Transformation.DISK_TRANSFORMATION);
                    break;
                case 2:
                    transformations.add(Transformation.HEART_TRANSFORMATION);
                    break;
                case 3:
                    transformations.add(Transformation.SINUS_TRANSFORMATION);
                    break;
                case 4:
                    transformations.add(Transformation.SPHERICAL_TRANSFORMATION);
                    break;
                case 5:
                    transformations.add(Transformation.TAN_TRANSFORMATION);
                    break;
                case 6:
                    transformations.add(Transformation.FISH_EYE_TRANSFORMATION);
                    break;
                case 7:
                    transformations.add(Transformation.LINEAR_TRANSFORMATION);
                    break;

                default:
                    break;
            }
        } while (choice != 0 || transformations.isEmpty());

        LOGGER.info("Enter minX, maxX, minY, maxY");
        double minX = in.nextDouble();
        double maxX = in.nextDouble();
        double minY = in.nextDouble();
        double maxY = in.nextDouble();

        LOGGER.info("Working...");

        var start = System.nanoTime();

        var result = renderer.render(
            samplesCount,
            iterationsPerSample,
            affinsCount,
            transformations,
            new Rectangle(minX, maxX, minY, maxY),
            symmetry,
            width,
            height
        );

        var end = System.nanoTime();
        CorrectionUtil.gammaCorrection(result);

        LOGGER.info("Time: " + (end - start) / Math.pow(10, 9));

        String format;
        do {
            LOGGER.info("Enter image format (png, jpeg, bmp): ");
            format = in.next();
        } while (!(format.equals("png") || format.equals("jpeg") || format.equals("bmp")));

        LOGGER.info("Enter file name: ");
        String name = in.next();

        ImageUtil.saveFractalImage(result, format, name + "." + format);
        LOGGER.info("###End###");
    }

//    public static void main(String[] args) {
//        Renderer renderer = new OneThreadRenderer();
//
//        var start = System.nanoTime();
//
//        renderer.render(
//            20000,
//            2000,
//            6,
//            List.of(Transformation.DISK_TRANSFORMATION),
//            new Rectangle(-1.777, 1.777, -1, 1),
//            1,
//            1920,
//            1080
//        );
//
//        var end = System.nanoTime();
//
//        var oneThreadTime = (end - start) / Math.pow(10, 9);
//
//        LOGGER.info("Time: " + oneThreadTime);
//
//        for (int i = 2; i < 13; i += 2) {
//            renderer = new MultiThreadingRenderer(i);
//            start = System.nanoTime();
//
//            renderer.render(
//                20000,
//                2000,
//                6,
//                List.of(Transformation.DISK_TRANSFORMATION),
//                new Rectangle(-1.777, 1.777, -1, 1),
//                1,
//                1920,
//                1080
//            );
//
//            end = System.nanoTime();
//
//            var time = (end - start) / Math.pow(10, 9);
//
//            LOGGER.info("Time of " + i + " threads: " + time);
//            LOGGER.info("Times faster:" + oneThreadTime / time);
//        }
//    }
}
