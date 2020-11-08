import java.util.Arrays;

public class Hw1_P1 {


    /**
     * The stats method takes a Integer array and computes the average
     * and finds the min and max , it then prints these values
     * @param a : Integer array
     */
    public static void stats(int[] a) {
        //Check if array has no elements
        if (a.length == 0) {
            System.out.println("Array is empty");
            return;
        }
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
     *  The subarray method takes a array and returns a subarray between the two given indexes
     *  @param a : Integer array
     *  @param from : Starting index
     *  @param to : Ending index
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
        System.out.println(String.format("The subarray, from index %d to index %d, is: %s",
                from, to, intArrayToString(result)));

    }

    /**
     * Helper method to convert integer array to a comma separated string
     * @param arr : Integer array
     */
    public static String intArrayToString(int[] arr){
        String result="";
        for( Integer i : arr){
            result = result + i.toString() + ",";
        }
        if (result.length()>0)
            result = result.substring(0, result.length()-1);
        return result;
    }


    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        // test
        int[] a = {15, 25, 10, 65, 30, 55, 65};

        System.out.println("\nGiven array is: " + intArrayToString(a));
        stats(a);
        subarray(a, 1, 4);

        // array with negatives and index out of bound
        int[] b = {-1,0,10,-2};
        System.out.println("\nGiven array is: " + intArrayToString(b));
        stats(b);
        subarray(b, 1, 4);


        // Test with huge numbers
        int[] c = {999999999,999999999,999999999,999999999};
        System.out.println("\nGiven array is: " + intArrayToString(c));
        stats(c);
        subarray(c, 1, 1);

        //Test null array
        int[] d = {};
        System.out.println("\nGiven array is: " + intArrayToString(d));
        stats(d);
        subarray(d, 0, 0);
    }

}