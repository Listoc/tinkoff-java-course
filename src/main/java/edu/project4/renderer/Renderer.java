package edu.project4.renderer;

import edu.project4.model.AffinCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"ParameterNumber"})
public interface Renderer {
    FractalImage render(
        int samplesCount,
        int iterationsPerSample,
        int affinCount,
        @NotNull List<Transformation> transformations,
        @NotNull Rectangle rect,
        int symmetry,
        int imageWidth,
        int imageHeight
    );

    default Point applyAffin(Point p, AffinCoefficients coeffs) {
        return new Point(
            coeffs.a() * p.x() + coeffs.b() * p.y() + coeffs.c(),
            coeffs.d() * p.x() + coeffs.e() * p.y() + coeffs.f()
        );
    }

    default void checkInput(
        int samplesCount,
        int iterationsPerSample,
        int affinCount,
        int symmetry,
        List<Transformation> transformations
    ) {
        if (samplesCount < 1 || iterationsPerSample < 1 || affinCount < 1 | symmetry < 1) {
            throw new IllegalArgumentException("All integer values must be greater than 0");
        }

        if (transformations.isEmpty()) {
            throw new IllegalArgumentException("You need to add at leat one transformation");
        }
    }
}
