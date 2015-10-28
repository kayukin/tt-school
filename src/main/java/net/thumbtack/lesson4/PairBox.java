package net.thumbtack.lesson4;

import net.thumbtack.lesson1.Circle;
import net.thumbtack.lesson1.Rectangle;
import net.thumbtack.lesson3.Figure;

/**
 * Created by kayukin on 25.10.15.
 */
public class PairBox<T extends Figure, K extends Figure> {
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
