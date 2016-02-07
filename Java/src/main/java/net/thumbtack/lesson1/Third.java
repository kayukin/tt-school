package net.thumbtack.lesson1;

/**
 * Created by kayuk_000 on 04.10.2015.
 */
public class Third {
    public static void main(String[] args) {
        double x = 5.5, y = 3.9;
        System.out.print("x+y=");
        System.out.println(x + y);
        System.out.print("x*y=");
        System.out.println(x * y);
        if (x > y)
            System.out.println("x>y");
        else if (x < y)
            System.out.println("x<y");
        else
            System.out.println("x=y");
    }
}
