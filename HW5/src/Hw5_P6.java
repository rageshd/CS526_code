import java.util.Comparator;

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
        sort.heapSort(a);
        for (Integer i : a)
            System.out.println(i);


    }

}
