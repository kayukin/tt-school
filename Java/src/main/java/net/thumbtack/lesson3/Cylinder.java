package net.thumbtack.lesson3;

import javafx.geometry.Point3D;
import net.thumbtack.lesson1.Circle;
import net.thumbtack.lesson4.Square;

/**
 * Created by kayukin on 18.10.15.
 */
public class Cylinder extends Circle implements Square, Figure {
    protected double height;
    protected Point3D center;

    public Cylinder(double x, double y, double z, double radius, double height) {
        center = new Point3D(x, y, z);
        this.radius = radius;
        this.height = height;

    }

    public Cylinder() {
        this(0, 0, 0, 1, 1);
    }
}
