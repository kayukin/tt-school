package net.thumbtack.lesson4;

import net.thumbtack.lesson1.Rectangle;
import net.thumbtack.lesson3.Rectangle3D;

/**
 * Created by kayukin on 25.10.15.
 */
public class Main {
    public static void main(String[] args) {
        Box<Rectangle> rectangleBox = new Box<>(new Rectangle(100, 100));
        Box<Rectangle3D> rectangle3DBox = new Box<>(new Rectangle3D(100, 100, 100));
        //Box<String> stringBox = new Box<>("abc");
    }

    public static boolean isSameSquareStatic(Box<?> firstBox, Box<?> secondBox) {
        return firstBox.isSameSquare(secondBox);
    }
}
