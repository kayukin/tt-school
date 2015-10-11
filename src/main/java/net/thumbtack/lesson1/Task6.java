package net.thumbtack.lesson1;

/**
 * Created by kayuk_000 on 04.10.2015.
 */
public class Task6 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 9};
        boolean IsInc = false, IsDec = false;
        if (arr[0] > arr[1])
            IsDec = true;
        else
            IsInc = true;
        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] > arr[i - 1]) && IsDec) {
                IsDec = false;
                break;
            }
            else if((arr[i] < arr[i - 1]) && IsInc){
                IsInc=false;
                break;
            }
        }

        if(IsDec)
            System.out.println("Sort by dec");
        else if(IsInc)
            System.out.println("Sort by inc");
        else
            System.out.println("Unsorted");

    }
}
