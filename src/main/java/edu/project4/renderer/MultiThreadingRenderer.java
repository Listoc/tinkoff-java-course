package edu.project4.renderer;

import edu.project4.model.AffinCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"ParameterNumber", "LambdaBodyLength"})
public class MultiThreadingRenderer implements Renderer {
    public static final int START_STEP = -20;
    private final int threadCount;
    private FractalImage fractalImage;
    private int imageWidth;
    private int imageHeight;
    private int iterationsPerSample;
    private Rectangle rect;
    private List<Transformation> nonLinearVariations;
    private final List<AffinCoefficients> affinList = new ArrayList<>();
    private int symmetry;

    public MultiThreadingRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    public MultiThreadingRenderer() {
        this(1);
    }

    @Override
    public FractalImage render(
        int samplesCount,
        int iterationsPerSample,
        int affinCount,
        @NotNull List<Transformation> nonLinearVariations,
        @NotNull Rectangle rect,
        int symmetry,
        int imageWidth,
        int imageHeight
    ) {
        checkInput(samplesCount, iterationsPerSample, affinCount, symmetry, nonLinearVariations);

        this.fractalImage = new FractalImage(imageWidth, imageHeight);
        this.imageHeight = imageHeight - 1;
        this.imageWidth = imageWidth - 1;
        this.iterationsPerSample = iterationsPerSample;
        this.rect = rect;
        this.nonLinearVariations = nonLinearVariations;
        this.symmetry = symmetry;

        int samplesPerThread = samplesCount / threadCount;

        for (int i = 0; i < affinCount; ++i) {
            affinList.add(AffinCoefficients.getRandomAffin());
        }
        var listOfThreads = new LinkedList<Thread>();
        listOfThreads.add(new Thread(getRunner(samplesPerThread + samplesCount % threadCount)));
        listOfThreads.addAll(
            Stream.generate(() -> new Thread(getRunner(samplesPerThread)))
                .limit(threadCount - 1)
                .toList()
        );
        listOfThreads.forEach(Thread::start);

        try {
            for (var thread : listOfThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return fractalImage;
    }

    private Runnable getRunner(int samples) {
        return () -> {
            var random = ThreadLocalRandom.current();

            for (int i = 0; i < samples; ++i) {
                var point = new Point(
                    random.nextDouble(rect.minX(), rect.maxX()),
                    random.nextDouble(rect.minY(), rect.maxY())
                );

                for (int step = START_STEP; step < iterationsPerSample; step++) {
                    int currentNonLinearVariationIndex = random.nextInt(0, nonLinearVariations.size());
                    int currentAffinIndex = random.nextInt(0, affinList.size());

                    var affin = affinList.get(currentAffinIndex);

                    point = applyAffin(point, affin);

                    point = nonLinearVariations.get(currentNonLinearVariationIndex).apply(point);

                    if (step >= 0) {
                        double theta2 = 0.0;
                        for (int s = 0; s < symmetry; ++s, theta2 += Math.PI * 2 / symmetry) {

                            point = new Point(
                                point.x() * Math.cos(theta2) - point.y() * Math.sin(theta2),
                                point.x() * Math.sin(theta2) - point.y() * Math.cos(theta2)
                            );

                            if (point.x() <= rect.maxX()
                                && point.x() >= rect.minX()
                                && point.y() <= rect.maxY()
                                && point.y() >= rect.minY()) {

                                int x1 = imageWidth
                                    - (int) (((rect.maxX() - point.x()) / (rect.maxX() - rect.minX())) * imageWidth);
                                int y1 = imageHeight
                                    - (int) (((rect.maxY() - point.y()) / (rect.maxY() - rect.minY())) * imageHeight);

                                var pixel = fractalImage.getData()[x1][y1];

                                synchronized (fractalImage.getData()[x1][y1]) {
                                    if (pixel.getHitCount() == 0) {
                                        pixel.setR(affin.rColor());
                                        pixel.setG(affin.gColor());
                                        pixel.setB(affin.bColor());
                                    } else {
                                        pixel.setR((pixel.getR() + affin.rColor()) / 2);
                                        pixel.setG((pixel.getG() + affin.gColor()) / 2);
                                        pixel.setB((pixel.getB() + affin.bColor()) / 2);
                                    }

                                    pixel.incrementHitCount();
                                }
                            }
                        }
                    }
                }
            }
        };
    }
}
