package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class Countdown {
    public static void main(String[] args) throws InterruptedException {
        int time = Integer.parseInt(args[0]);
        Queue<Integer> queue = new LinkedList<>();

        for (int i = time; i >= 0; i--) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
            Thread.sleep(1000);
        }

    }

    static <E> List<E> heapSort(Collection<E> c) {
        Queue<E> queue = new PriorityQueue<>(c);
        List<E> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            result.add(queue.remove());
        }
        return result;
        
    }
}
