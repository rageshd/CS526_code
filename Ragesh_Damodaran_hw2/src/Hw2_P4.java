import java.util.Arrays;

public class Hw2_P4 {
    /**
     * Reverse the first N elements in an array
     * @param a : Integer array
     * @param n : Number of elements to reverse
     */
    public static void reverseFirstN(int[] a, int n) {
        // Handling null array
        if (a==null){
            System.out.println("Error : Array is null");
            return;
        }
        // Handling incorrect value of n
        if (n<0 || n>=a.length){
            System.out.printf("Error : Incorrect value for n (%d), " +
                    "enter value greater than 0 and less than  %d\n", n, a.length);
            return;
        }

        // create a target to half of number of elements
        int target = (n-1)/2;
        recurseReverse(a, target, n-1, 0);

    }

    /**
     * Recursively reverses portion of an array
     * @param a : Array
     * @param target : base case limit
     * @param upper : upper index of swap
     * @param lower : lower index of swap
     */
    private static void recurseReverse(int[] a, int target, int upper, int lower) {
        // Base case
        if (upper <= target)
            return;
        else{
            // swap the upper element and lower index elements
            int temp = a[lower];
            a[lower] = a[upper];
            a[upper] = temp;
            // Recursive call
            recurseReverse(a, target, upper-1, lower+1);
        }
    }

    /**
     * function to move even numbers in an array before the odd numbers
     * @param a : Integer array
     */
    public static void evenBeforeOdd(int[] a) {
        // Call recursive method
        recurseEvenBeforeOdd(a, 0, 0);
    }

    /**
     * Recursive function to move even numbers before the odd one's
     * @param a : Integer array
     * @param swapIndex : Index to swap with
     * @param currentIndex : The current index to process
     */
    private static void recurseEvenBeforeOdd(int[] a, int swapIndex, int currentIndex) {
        // Base case
        if ( currentIndex > a.length -1 ){
            return;
        }
        // Swap out element at currentIndex if its even with the one at swapIndex
        if (a[currentIndex]%2 == 0 ){
            int temp = a[currentIndex];
            a[currentIndex] = a[swapIndex];
            a[swapIndex] = temp;
            swapIndex++;
        }
        currentIndex++;

        // Recursive call
        recurseEvenBeforeOdd(a, swapIndex, currentIndex);

    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {

        int[] a = new int[10];

        for (int i=0; i<a.length; i++) {
            a[i]= (i+1) * 10;
        }

        System.out.println("Initial array: ");
        System.out.println(Arrays.toString(a));
        System.out.println();

        // make a copy and use it for testing
        int[] intArrayCopy;
        intArrayCopy = a.clone();

        int N = 2;
        reverseFirstN(intArrayCopy, N);
        System.out.println("\nAfter reversing first " + N + " elements: ");
        System.out.println(Arrays.toString(intArrayCopy));
        System.out.println();

        intArrayCopy = a.clone();
        N = 7;
        reverseFirstN(intArrayCopy, N);
        System.out.println("\nAfter reversing first " + N + " elements: ");
        System.out.println(Arrays.toString(intArrayCopy));
        System.out.println();

        //Incorrect value for N
        N = 50;
        reverseFirstN(intArrayCopy, N);
        System.out.println("\nAfter reversing first " + N + " elements: ");
        System.out.println(Arrays.toString(intArrayCopy));
        System.out.println();

        int[] b = {10, 15, 20, 30, 25, 35, 40, 45};
        System.out.println("\nBefore rearrange: ");
        System.out.println(Arrays.toString(b));
        System.out.println();

        evenBeforeOdd(b);
        System.out.println("\nAfter rearrange: ");
        System.out.println(Arrays.toString(b));
        System.out.println();


        // Only even case
        int[] c = {10, 20, 30};
        System.out.println("\nBefore rearrange: ");
        System.out.println(Arrays.toString(c));
        System.out.println();

        evenBeforeOdd(c);
        System.out.println("\nAfter rearrange: ");
        System.out.println(Arrays.toString(c));
        System.out.println();

        // Last element is even case
        int[] d = {47, 79, 97, 20};
        System.out.println("\nBefore rearrange: ");
        System.out.println(Arrays.toString(d));
        System.out.println();

        evenBeforeOdd(d);
        System.out.println("\nAfter rearrange: ");
        System.out.println(Arrays.toString(d));
        System.out.println();

        // test empty array
        int[] emptyArray = null;
        System.out.println("Initial array: ");
        System.out.println(Arrays.toString(emptyArray));
        System.out.println();

        reverseFirstN(emptyArray, 2);

        System.out.println("After reversing: ");
        System.out.println(Arrays.toString(emptyArray));
        System.out.println();


    }
}
