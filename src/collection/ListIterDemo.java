package collection;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ListIterDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        for (ListIterator<Integer> iter = list.listIterator(list.size()); iter.hasPrevious(); ) {
            System.out.println(iter.previous());
        }
    }
}
