package net.thumbtack.lesson1;

import java.awt.geom.Point2D;

/**
 * Created by kayuk_000 on 04.10.2015.
 */
public class Task7 {
    public static void main(String[] args) {
        net.thumbtack.lesson1.Rectangle rect=new net.thumbtack.lesson1.Rectangle(20,20);
        System.out.println(rect);
        boolean b = rect.IsPointIn(new Point2D.Double(10, 10));

    }
}
