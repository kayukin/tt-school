import java.awt.geom.Point2D;

/**
 * Created by kayuk_000 on 05.10.2015.
 */
public class Triangle {
    Point a;
    Point b;
    Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public Point getB() {

        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Point getA() {

        return a;
    }

    @Override
    public String toString() {
        return "A" + a +
                " B" + b +
                " C=" + c;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public void Move(double dx, double dy) {
        a.setX(a.getX() + dx);
        a.setY(a.getY() + dy);
        b.setX(b.getX() + dx);
        b.setY(b.getY() + dy);
        c.setX(c.getX() + dx);
        c.setY(c.getY() + dy);
    }

    public double Area() {
        double AB = a.Distance(b);
        double BC = b.Distance(c);
        double AC = a.Distance(c);
        double p = (AB + BC + AC) / 2;
        return Math.sqrt(p * (p - AB) * (p - BC) * (p - AC));
    }

    public boolean IsPointIn(double x, double y) {
        Point point = new Point(x, y);
        double q = (a.getX() - point.getX()) * (b.getY() - a.getY()) - (b.getX() - a.getX()) * (a.getY() - point.getY());
        double w = (b.getX() - point.getX()) * (c.getY() - b.getY()) - (c.getX() - b.getX()) * (b.getY() - point.getY());
        double e = (c.getX() - point.getX()) * (a.getY() - c.getY()) - (a.getX() - c.getX()) * (c.getY() - point.getY());
        return ((q >= 0 && w >= 0 && e >= 0) || (q <= 0 && w <= 0 && e <= 0));
    }

    public boolean IsPointIn(Point2D point) {
        return IsPointIn(point.getX(), point.getY());
    }

    public boolean IsEquilateral() {
        return a.Distance(b) == a.Distance(c) && a.Distance(b) == b.Distance(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (!a.equals(triangle.a)) return false;
        if (!b.equals(triangle.b)) return false;
        return c.equals(triangle.c);

    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        return result;
    }
}
