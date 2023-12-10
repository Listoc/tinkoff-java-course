package edu.project4.util;

import edu.project4.model.FractalImage;
import org.jetbrains.annotations.NotNull;

public class CorrectionUtil {
    private static final double GAMMA = 2.2;

    public static void gammaCorrection(@NotNull FractalImage image) {
        double max = 0.0;

        for (int row = 0; row < image.getWidth(); ++row) {
            for (int col = 0; col < image.getHeight(); col++) {
                var pixel = image.getData()[row][col];
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    if (pixel.getNormal() > max) {
                        max = pixel.getNormal();
                    }
                }
            }
        }

        for (int row = 0; row < image.getWidth(); ++row) {
            for (int col = 0; col < image.getHeight(); col++) {
                var pixel = image.getData()[row][col];

                pixel.setNormal(pixel.getNormal() / max);

                pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormal(), 1.0 / GAMMA)));
                pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormal(), 1.0 / GAMMA)));
                pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormal(), 1.0 / GAMMA)));
            }
        }
    }

    private CorrectionUtil() {}
}
