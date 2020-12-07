import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Sorting<K> {

    /**
     * InsertionSort
     * @param data
     */
    public  void insertionSort(Integer[] data) {
        int n = data.length;
        for (int k = 1; k < n; k++) {
// begin with second character /
// / time to insert cur=data[k] // find correct index j for cur // thus, data[j-1] must go after cur // slide data[j-1] rightward
// and consider previous j for cur // this is the proper place for cur
            int cur = data[k];
            int j = k;
            while (j > 0 && data[j-1]>cur){
                data[j] = data[j-1];
                j--;
                data[j] = cur;
            }
        }
   }


    /**
     * Merge Sort
      * @param S
     * @param comp
     * @param <K>
     */
   public <K> void mergeSort(K[ ] S, Comparator<K> comp) {
       int n = S.length;
       if (n < 2)
           return;
       int mid = n/2;
       K[ ] S1 = Arrays.copyOfRange(S, 0, mid);
       K[ ] S2 = Arrays.copyOfRange(S, mid, n);
       // conquer (with recursion)
       mergeSort(S1, comp);
       mergeSort(S2, comp);
       // merge results
       merge(S1, S2, S, comp);
   }

   public <K> void merge(K[ ] S1, K[ ] S2, K[ ] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i + j] = S1[i++];    // copy ith element of S1 and increment i
            else
                S[i + j] = S2[j++];    // copy jth element of S2 and increment j
        }
   }

    /**
     * Quick sort
     * @param S
     * @param comp
     * @param a
     * @param b
     * @param <K>
     */
    public <K> void quickSortInPlace(K[ ] S, Comparator<K> comp,int a, int b) {
        if (a >= b)
            return;
        int left = a;
        int right = b - 1;
        K pivot = S[b];
        K temp;
        while (left <= right) {
            while (left <= right && comp.compare(S[left], pivot) < 0)
                left++; // scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && comp.compare(S[right], pivot) > 0)
                right--;
            if (left <= right) {    // indices did not strictly cross
                // so swap values and shrink range
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;
                left++;
                right--;
            }
        }
        // put pivot into its final place (currently marked by left index)
        temp = S[left];
        S[left] = S[b];
        S[b] = temp;
        // make recursive calls
        quickSortInPlace(S, comp, a, left - 1);
        quickSortInPlace(S, comp, left + 1, b);
    }



    /**
     * Heapsort from https://www.geeksforgeeks.org/java-program-for-heap-sort/
     * @param arr
     */
    public void heapSort(Integer[] arr)
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(Integer[] arr, int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }


}
