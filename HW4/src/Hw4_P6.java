import java.util.*;

public class Hw4_P6 {
    /**
     * Main function
     * @param args
     */
    public static void main(String[] args){
        // Declare Constants
        final int ARRAY_SIZE = 100000;
        // Declare the variables
        HashMap myMap = new HashMap();
        ArrayList myArrayList = new ArrayList();
        LinkedList LinkedList = new LinkedList();
        int[] insertKeys = new int[ARRAY_SIZE];
        int[] searchKeys = new int[ARRAY_SIZE];

        // Generate random integers and populate the insertKeys array
        Random r = new Random(System.currentTimeMillis() );
        for( int i = 0; i< ARRAY_SIZE; i++)
            insertKeys[i] = r.nextInt(ARRAY_SIZE) + 1;

        // Reseed the random
        r.setSeed(System.currentTimeMillis());

        // Generate random integers and populate the searchKeys array
        for( int i = 0; i< ARRAY_SIZE; i++)
            searchKeys[i] = r.nextInt(ARRAY_SIZE) + 1;


    }
}
