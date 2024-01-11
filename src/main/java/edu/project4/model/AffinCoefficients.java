package edu.project4.model;

import java.util.concurrent.ThreadLocalRandom;
import static edu.project4.model.Pixel.MAX_COLOR;
import static edu.project4.model.Pixel.MIN_COLOR;

public record AffinCoefficients(
    double a,
    double b,
    double c,
    double d,
    double e,
    double f,
    int rColor,
    int gColor,
    int bColor) {
    public static final double MAX_COEFFICIENT = 1;
    public static final double MIN_COEFFICIENT = -1;

    public static AffinCoefficients getRandomAffin() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;
        int red;
        int green;
        int blue;
        var random = ThreadLocalRandom.current();

        do {
            do {
                a = random.nextDouble(MIN_COEFFICIENT, MAX_COEFFICIENT);
                d = random.nextDouble(MIN_COEFFICIENT, MAX_COEFFICIENT);
                } while ((a * a + d * d) > 1);

            do {
                b = random.nextDouble(MIN_COEFFICIENT, MAX_COEFFICIENT);
                e = random.nextDouble(MIN_COEFFICIENT, MAX_COEFFICIENT);
            } while ((b * b + e * e) > 1);
        } while (a * a + b * b + d * d + e * e > 1 + (a * e - d * b) * (a * e - d * b));

            f = random.nextDouble(MIN_COEFFICIENT, MAX_COEFFICIENT);
            c = random.nextDouble(MIN_COEFFICIENT, MAX_COEFFICIENT);

        red = random.nextInt(MIN_COLOR, MAX_COLOR + 1);
        green = random.nextInt(MIN_COLOR, MAX_COLOR + 1);
        blue = random.nextInt(MIN_COLOR, MAX_COLOR + 1);

        return new AffinCoefficients(a, b, c, d, e, f, red, green, blue);
    }
}
