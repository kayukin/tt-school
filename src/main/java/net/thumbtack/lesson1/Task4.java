package net.thumbtack.lesson1;

import java.awt.geom.Point2D;

/**
 * Created by kayuk_000 on 04.10.2015.
 */
public class Task4 {
    public static void main(String[] args) {
        int x1 = 0, y1 = 0, x2 = 10, y2 = 20;
        int x = 20, y = 20;

        if ((x > x1 && x < x2 && y > y1 && y < y2) || (x < x1 && x > x2 && y < y1 && y > y2))
            System.out.println("Точка лежит в прямоугольнике");
        else
            System.out.println("Точка не лежит в прямоугольнике");

    }
}
