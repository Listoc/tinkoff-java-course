package edu.project4.transformation;

import edu.project4.model.Point;
import java.util.function.Function;

@FunctionalInterface
public interface Transformation extends Function<Point, Point> {
    Transformation LINEAR_TRANSFORMATION = (p) -> p;
    Transformation SINUS_TRANSFORMATION = (p) -> new Point(Math.sin(p.x()), Math.sin(p.y()));
    Transformation TAN_TRANSFORMATION = (p) -> new Point(Math.sin(p.x()) / Math.cos(p.y()), Math.tan(p.y()));
    Transformation SPHERICAL_TRANSFORMATION = (p) -> new Point(
        p.x() / (p.x() * p.x() + p.y() * p.y()),
        p.y() / (p.x() * p.x() + p.y() * p.y())
    );
    Transformation DISK_TRANSFORMATION = (p) -> {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double theta = Math.atan(p.x() / p.y()) / Math.PI;
        return new Point(theta * Math.sin(Math.PI * r), theta * Math.cos(Math.PI * r));
    };
    Transformation HEART_TRANSFORMATION = (p) -> {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double theta = Math.atan(p.x() / p.y()) * r;
        return new Point(r * Math.sin(theta), r * -Math.cos(theta));
    };
    Transformation FISH_EYE_TRANSFORMATION = (p) -> {
        double r = Math.sqrt(p.x() * p.x() + p.y() * p.y());
        double theta = 2 / (r + 1);
        return new Point(p.x() * theta, p.y() * theta);
    };

    Point apply(Point point);
}
