/**
 * Created by kayuk_000 on 08.10.2015.
 */
public final class CircleFactory {
    private static int count;

    private CircleFactory() {
        count = 0;
    }

    public static int getCount() {
        return count;
    }

    public static Circle createCircle() {
        count++;
        return new Circle(new Point(Math.random() * 100, Math.random() * 100), Math.random() * 100);
    }
}
