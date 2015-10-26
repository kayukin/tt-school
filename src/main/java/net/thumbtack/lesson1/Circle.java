package net.thumbtack.lesson1;

import net.thumbtack.lesson4.Square;

import java.awt.geom.Point2D;

/**
 * Created by kayuk_000 on 06.10.2015.
 */
public class Circle implements Square {
    protected Point center;
    protected double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle() {
        this(new Point(0, 0), 1);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return center + " radius=" + radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (Double.compare(circle.radius, radius) != 0) return false;
        return center.equals(circle.center);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = center.hashCode();
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void Move(double dx, double dy) {
        center.setX(center.getX() + dx);
        center.setY(center.getY() + dy);
    }

    public double Length() {
        return 2 * Math.PI * radius;
    }

    public boolean IsPointIn(double x, double y) {
        return (((x - center.getX()) * (x - center.getX()) + (y - center.getY()) * (y - center.getY())) <= radius * radius);
    }

    public boolean IsPointIn(Point2D point) {
        return IsPointIn(point.getX(), point.getY());
    }

    @Override
    public double square() {
        return Math.PI * radius * radius;
    }
}
