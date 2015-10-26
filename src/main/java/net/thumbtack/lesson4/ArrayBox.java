package net.thumbtack.lesson4;

/**
 * Created by kayukin on 25.10.15.
 */
public class ArrayBox<T extends Square> {
    T[] values;

    public ArrayBox(T[] values) {
        this.values = values;
    }

    public T[] getValues() {
        return values;
    }

    public void setValues(T[] values) {
        this.values = values;
    }
}
