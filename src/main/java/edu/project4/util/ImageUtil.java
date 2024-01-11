package edu.project4.util;

import edu.project4.model.FractalImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.NotNull;

public class ImageUtil {
    private static final int RED_SHIFT = 16;
    private static final int GREEN_SHIFT = 8;

    public static void saveFractalImage(
        @NotNull FractalImage fractalImage,
        @NotNull String format,
        @NotNull String fileName
    ) {

        var image = new BufferedImage(fractalImage.getWidth(), fractalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < fractalImage.getWidth(); ++i) {
            for (int j = 0; j < fractalImage.getHeight(); ++j) {
                image.setRGB(
                    i,
                    j,
                    fractalImage.getData()[i][j].getB()
                        + (fractalImage.getData()[i][j].getR() << RED_SHIFT)
                        + (fractalImage.getData()[i][j].getG() << GREEN_SHIFT));
            }
        }

        try {
            ImageIO.write(image, format, new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't save image");
        }
    }

    private ImageUtil() {}
}
