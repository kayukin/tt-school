package net.thumbtack.lesson4;

/**
 * Created by kayukin on 25.10.15.
 */
public class NamedArrayBox<T extends Square> extends ArrayBox<T> {
    String nameOfBox;

    public NamedArrayBox(T[] values) {
        super(values);
    }

    public String getNameOfBox() {
        return nameOfBox;
    }

    public void setNameOfBox(String nameOfBox) {
        this.nameOfBox = nameOfBox;
    }
}
