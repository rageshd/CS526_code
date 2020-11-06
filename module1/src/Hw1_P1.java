import java.util.Arrays;

public class Hw1_P1 {

    /**
     * Description:
     *  The stats method takes a Integer array and computes the average
     * and finds the min and max , it then prints these values
     * Inputs:
     *  @param a : Integer array
     * Outputs:
     *  None
     */
    public static void stats(int[] a) {
        double average = 0;
        int tempCnt = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // Loop through the array and find average and find min and max
        for (int i: a){
            if (i > max)
                max = i;
            if (i<min)
                min = i;

            // We add the contribution of each element to calculate total average
            average += (i-average) / tempCnt;
            tempCnt++;
        }

        // Print the result
        System.out.println(String.format("average = %.2f, min = %d , max = %d", average, min, max));
    }

    /**
     * Description:
     *  The subarray method takes a array and returns a subarray between the two given indexes
     * Inputs:
     *  @param a : Integer array
     *  @param from : Starting index
     *  @param to : Ending index
     * Output:
     *  None
     */
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

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        // test
        int[] a = {15, 25, 10, 65, 30, 55, 65};

        System.out.println("\nGiven array is: " + Arrays.toString(a));
        stats(a);
        subarray(a, 1, 4);

        // array with negatives and index out of bound
        int[] b = {-1,0,10,-2};
        System.out.println("\nGiven array is: " + Arrays.toString(b));
        stats(b);
        subarray(b, 1, 4);


        // Test with huge numbers
        int[] c = {999999999,999999999,999999999,999999999};
        System.out.println("\nGiven array is: " + Arrays.toString(c));
        stats(c);
    }

}