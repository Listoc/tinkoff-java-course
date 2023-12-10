package edu.project4.renderer;

import edu.project4.model.AffinCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"ParameterNumber"})
public class OneThreadRenderer implements Renderer {

    public static final int START_STEP = -20;

    @Override
    public FractalImage render(
        int samplesCount,
        int iterationsPerSample,
        int affinCount,
        @NotNull List<Transformation> nonLinearVariations,
        @NotNull Rectangle rect,
        int symmetry,
        int imageWidthArg,
        int imageHeightArg
    ) {
        checkInput(samplesCount, iterationsPerSample, affinCount, symmetry, nonLinearVariations);

        var fractalImage = new FractalImage(imageWidthArg, imageHeightArg);
        int imageHeight = imageHeightArg - 1;
        int imageWidth = imageWidthArg - 1;

        var affinList = new ArrayList<AffinCoefficients>();
        var random = ThreadLocalRandom.current();

        for (int i = 0; i < affinCount; ++i) {
            affinList.add(AffinCoefficients.getRandomAffin());
        }

        for (int i = 0; i < samplesCount; ++i) {
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

        return fractalImage;
    }
}
