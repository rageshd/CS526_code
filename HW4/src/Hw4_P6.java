import java.util.*;

public class Hw4_P6 {
    /**
     * Main function
     * @param args
     */
    public static void main(String[] args){

        // Declare Constants
        final int ARRAY_SIZE = 100000;
        final int LOOP_CNT = 10;

        // Declare the variables
        HashMap myMap ;
        ArrayList myArrayList ;
        LinkedList myLinkedList ;
        HashSet duplicateDetectionSet;
        int[] insertKeys = new int[ARRAY_SIZE];
        int[] searchKeys = new int[ARRAY_SIZE];
        long startTime, endTime, elapsedTime;
        long totalMapInsertTime = 0, totalArrayListInsertTime=0, totalLinkedListInsertTime=0;
        long totalMapSearchTime=0, totalArrayListSearchTime=0, totalLinkedListSearchTime=0;

        // Generate random integers
        Random r = new Random(System.currentTimeMillis() );

        // Run the operations 10 times
        for (int j = 0; j < LOOP_CNT; j++) {
            System.out.println("Loop "+ j + " -> " + new Date());

            //Reinitialise variables
            myMap = new HashMap();
            myArrayList = new ArrayList();
            myLinkedList = new LinkedList();

            // Reseed the random
            r.setSeed(System.currentTimeMillis());
            // Populate the insertKeys
            duplicateDetectionSet = new HashSet();
            int k=0;
            while(k < ARRAY_SIZE) {
                int randomNum = r.nextInt(1000000) + 1;
                // Check if the random number was already present
                if (!duplicateDetectionSet.contains(randomNum)) {
                    insertKeys[k] = randomNum;
                    duplicateDetectionSet.add(randomNum);
                    k++;
                }
            }
            System.out.println(duplicateDetectionSet.size());
            // Insert into map
            startTime = System.currentTimeMillis();
            for (int i : insertKeys)
                myMap.put(i,i);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalMapInsertTime += elapsedTime;

            // Add to Arraylist
            startTime = System.currentTimeMillis();
            for (int i : insertKeys)
                myArrayList.add(i);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalArrayListInsertTime += elapsedTime;

            // Add to LinkedList
            startTime = System.currentTimeMillis();
            for (int i : insertKeys)
                myLinkedList.add(i);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalLinkedListInsertTime += elapsedTime;

            System.out.printf("Size of map is %d, size of arraylist is %d and size of linkedlist is %d \n",
                    myMap.size(), myArrayList.size(), myLinkedList.size());

            // Reseed the random
            r.setSeed(System.currentTimeMillis());

            // Generate random integers and populate the searchKeys array
            duplicateDetectionSet = new HashSet();
            k=0;
            while(k < ARRAY_SIZE) {
                int randomNum = r.nextInt(2000000) + 1;
                // Check if the random number was already present
                if (!duplicateDetectionSet.contains(randomNum)) {
                    searchKeys[k] = randomNum;
                    duplicateDetectionSet.add(randomNum);
                    k++;
                }
            }


            // Search in map
            startTime = System.currentTimeMillis();
            for (int i : searchKeys)
                myMap.get(i);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalMapSearchTime += elapsedTime;

            // Search in arraylist
            startTime = System.currentTimeMillis();
            for (int i : searchKeys)
                myArrayList.contains(i);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalArrayListSearchTime += elapsedTime;

            // Search in linkedlist
            startTime = System.currentTimeMillis();
            for (int i : searchKeys)
                myLinkedList.contains(i);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalLinkedListSearchTime += elapsedTime;

        }

        // Print the output
        System.out.printf("Number of keys = %d\n\n", ARRAY_SIZE);
        System.out.printf("HashMap average total insert time = %.2f ms \n", (double)totalMapInsertTime/LOOP_CNT);
        System.out.printf("ArrayList average total insert time = %.2f ms\n", (double)totalArrayListInsertTime/LOOP_CNT);
        System.out.printf("LinkedList average total insert time = %.2f ms\n\n", (double)totalLinkedListInsertTime/LOOP_CNT);

        System.out.printf("HashMap average total search time = %.2f ms\n", (double)totalMapSearchTime/LOOP_CNT);
        System.out.printf("ArrayList average total search time = %.2f ms \n", (double)totalArrayListSearchTime/LOOP_CNT);
        System.out.printf("LinkedList average total search time = %.2f ms \n", (double)totalLinkedListSearchTime/LOOP_CNT);


    }
}
