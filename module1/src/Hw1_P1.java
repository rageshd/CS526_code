import java.util.Arrays;

public class Hw1_P1 {

    public static void stats(int[] a) {
        int sum = 0;
        int count = a.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // Loop through the array and add to sum and find min and max
        for (int i: a){
            if (i > max)
                max = i;
            if (i<min)
                min = i;
            sum+=i;
        }
        // Calculate average
        float average = (float)sum/count;
        // Print the result
        System.out.println(String.format("average = %.2f, min = %d , max = %d", average, min, max));
    }

    public static void subarray(int[] a, int from, int to) {
        // error check w/o using Java's exception handling
        if (from < 0 || to >= a.length) {
            System.out.println("Index out of bound");
            return;
        }

        int[] result = new int[(to-from)+1];
        for (int i=from; i<=to; i++){
            int index = i-from;
            result[index] = a[i];
        }

        // Print the subarray
        System.out.println("The subarray, from index 1 to index 4, is: "+Arrays.toString(result));

    }

    public static void main(String[] args) {

        // test
        int[] a = {15, 25, 10, 65, 30, 55, 65};

        System.out.println("\nGiven array is: " + Arrays.toString(a));
        stats(a);
        subarray(a, 1, 4);

        // test with other arrays
    }

}