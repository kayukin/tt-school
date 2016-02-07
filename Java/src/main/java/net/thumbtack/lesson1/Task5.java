package net.thumbtack.lesson1;

/**
 * Created by kayuk_000 on 04.10.2015.
 */
public class Task5 {
    public static void main(String[] args) {
        int[] arr = {6, 3, 6, 8, 2, 0, 3, 12, 17, 23};
        int sum = 0;
        int prod = 1;
        int min = arr[0];
        int max = arr[0];
        for (int a : arr) {
            sum += a;
            prod *= a;
            if (a < min)
                min = a;
            if (a > max)
                max = a;
        }
        double avrg = (double) sum / arr.length;
        System.out.println("Sum = "+sum);
        System.out.println("Prod = "+prod);
        System.out.println("Min = "+min);
        System.out.println("Max = "+max);
        System.out.println("Avrg = "+avrg);
    }
}
