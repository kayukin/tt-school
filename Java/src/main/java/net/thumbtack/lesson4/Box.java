package net.thumbtack.lesson4;

import net.thumbtack.lesson1.Rectangle;

/**
 * Created by kayukin on 25.10.15.
 */
public class Box<T extends Square> implements Square {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public double square() {
        return value.square();
    }

    public boolean isSameSquare(Box<?> anotherBox) {
        return square() == anotherBox.square();
    }
}
