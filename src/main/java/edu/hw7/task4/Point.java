package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;

public record Point(long x, long y) {

    public static Point randomPoint(long radius) {
        var random = ThreadLocalRandom.current();

        return new Point(
            random.nextLong(2 * radius),
            random.nextLong(2 * radius)
            );
    }

    public boolean inCircle(long radius) {
        var centerPoint = new Point(radius, radius);

        return getSquareDistance(centerPoint) < radius * radius;
    }

    public double getSquareDistance(Point other) {
        long xDistance = (this.x - other.x) * (this.x - other.x);
        long yDistance = (this.y - other.y) * (this.y - other.y);

        return xDistance + yDistance;
    }
}
