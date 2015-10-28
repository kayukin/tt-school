package net.thumbtack.lesson3;

import javafx.geometry.Point3D;
import net.thumbtack.lesson1.Rectangle;
import net.thumbtack.lesson4.Square;

/**
 * Created by kayukin on 18.10.15.
 */
public class Rectangle3D extends Rectangle implements Square, Figure {
    double z;
    double length;

    public Rectangle3D() {
        super(1, 1);
        z = 0;
        length = 1;
    }

    public Rectangle3D(double x, double y, double z, double width, double height, double length) {
        super(x, y, width, height);
        this.z = z;
        this.length = length;
    }

    public Rectangle3D(double width, double height, double length) {
        super(width, height);
        z = 0;
        this.length = length;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "(" + x + ';' + y + ';' + z + ')';
    }


    public void Move(double dx, double dy, double dz) {
        super.Move(dx, dy);
        z += dz;
    }

    public double Volume() {
        return width * length * height;
    }

    public boolean IsPointIn(double x, double y, double z) {
        return super.IsPointIn(x, y) && z >= this.z && z <= this.z + length;
    }

    public boolean IsPointIn(Point3D point) {
        return IsPointIn(point.getX(), point.getY(), point.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Rectangle3D that = (Rectangle3D) o;

        if (Double.compare(that.z, z) != 0) return false;
        return Double.compare(that.length, length) == 0;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
