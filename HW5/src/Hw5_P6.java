import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

public class Hw5_P6<T> {
    public static void main(String[] args) {
        Integer[] a = {10, 9, 8, 7, 6, 5};
        Sorting<Integer> sort = new Sorting<Integer>();
        Comparator<Integer> in = new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1>o2?1:-1;
            }
        };
//        sort.quickSortInPlace(a, in, 0, a.length-1);
//        sort.heapSort(a);
//        for (Integer i : a)
//            System.out.println(i);


        for (int i=10000; i<=100000; i+=10000){
            // Hashset to preent duplicates
            HashSet duplicateDetectionSet = new HashSet();

            // Generate random integers
            Random r = new Random(System.currentTimeMillis() );

            //Array to hold the random numbers
            Integer[] unsortedArray = new Integer[i];

            // Insert random numbers to the array
            int k=0;
            while(k < i) {
                int randomNum = r.nextInt(1000000) + 1;
                // Check if the random number was already present
                if (!duplicateDetectionSet.contains(randomNum)) {
                    unsortedArray[k] = randomNum;
                    duplicateDetectionSet.add(randomNum);
                    k++;
                }
            }

            System.out.print("Size of array ("+unsortedArray.length+")");

            // Insertion sort
            Integer[] insertSortArray = unsortedArray.clone();
            long startTime = System.currentTimeMillis();
            sort.insertionSort(insertSortArray);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.print(" Insertion sort time : "+ elapsedTime + ",");

//            System.out.println(unsortedArray[100]+"-"+ insertSortArray[100]);

            // Quick sort
            Integer[] quickSortArray = unsortedArray.clone();
            startTime = System.currentTimeMillis();
            sort.quickSortInPlace(quickSortArray, in, 0, quickSortArray.length-1);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.print(" Quick sort time : "+ elapsedTime + ",");

            // Merge sort
            Integer[] mergeSortArray = unsortedArray.clone();
            startTime = System.currentTimeMillis();
            sort.mergeSort(mergeSortArray, in);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.print(" Merge sort time : "+ elapsedTime + ",");

//            System.out.println(unsortedArray[100]+"-"+ mergeSortArray[100]);

            // Heap sort
            Integer[] heapSortArray = unsortedArray.clone();
            startTime = System.currentTimeMillis();
            sort.heapSort(heapSortArray);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.print(" Heap sort time : "+ elapsedTime + ",");

            System.out.println();
        }
    }

}
