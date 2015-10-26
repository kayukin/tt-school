package net.thumbtack.lesson4;

import net.thumbtack.lesson1.Circle;
import net.thumbtack.lesson1.Rectangle;

/**
 * Created by kayukin on 25.10.15.
 */
public class PairBox<T extends Rectangle, K extends Circle> {
    T firstValue;
    K secondValue;

    public PairBox(T firstValue, K secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public boolean isSameSquare() {
        return firstValue.square() == secondValue.square();
    }
}
