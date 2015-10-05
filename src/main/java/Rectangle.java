import java.awt.geom.Point2D;

/**
 * Created by kayuk_000 on 04.10.2015.
 */
public class Rectangle {
    double x;
    double y;
    double height;
    double width;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height) {
        this(0, 0, width, height);
    }

    public Rectangle() {
        this(1, 1);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {

        this.height = height;
    }

    public void setY(double y) {

        this.y = y;
    }

    public void setX(double x) {

        this.x = x;
    }

    public double getWidth() {

        return width;
    }

    public double getHeight() {

        return height;
    }

    public double getY() {

        return y;
    }

    public double getX() {

        return x;
    }

    public void Move(double dx, double dy) {
        y += dy;
        x += dx;
    }

    public double sqrt() {
        return width * height;
    }

    public Rectangle Large() {
        return new Rectangle(x, y, width * 5, height * 5);
    }

    public void Compress(double nx, double ny) {
        width /= nx;
        height /= ny;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (Double.compare(rectangle.x, x) != 0) return false;
        if (Double.compare(rectangle.y, y) != 0) return false;
        if (Double.compare(rectangle.height, height) != 0) return false;
        return Double.compare(rectangle.width, width) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ") " +
                "(" + x + ";" + (y + height) + ") " +
                "(" + (x + width) + ";" + (y + height) + ") " +
                "(" + (x + width) + ";" + y + ") ";

    }

    public boolean IsPointIn(double x, double y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public boolean IsPointIn(Point2D point) {
        return IsPointIn(point.getX(), point.getY());
    }

    public boolean IsRectIn(Rectangle rectangle) {//доделать
        return rectangle.x <= this.x &&
                rectangle.y <= this.y &&
                true;
    }
}
