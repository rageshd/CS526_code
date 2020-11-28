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
            for (int i = 0; i < ARRAY_SIZE; i++)
                insertKeys[i] = r.nextInt(1000000) + 1;

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

            // Reseed the random
            r.setSeed(System.currentTimeMillis());

            // Generate random integers and populate the searchKeys array
            for (int i = 0; i < ARRAY_SIZE; i++)
                searchKeys[i] = r.nextInt(2000000) + 1;

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
