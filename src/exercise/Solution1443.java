package exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution1443
 */
public class Solution1443 {

    class Tuple {
        public int i;
        public int j;

        Tuple(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object object) {
            return this.i == ((Tuple)object).i && this.j == ((Tuple)object).j;
        }
    }

    Map<Tuple, Integer> map = new HashMap<>();

    public int countTriplets(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length ; j++) {
                for (int k = j ; k < arr.length; k++) {
                    if (ifEqual(arr, i, j, k)) {
                        System.out.format("%d, %d, %d%n", i, j, k);
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    private boolean ifEqual(int[] arr, int i, int j, int k) {
        int a, b;
        Tuple indexA = new Tuple(i, j);
        Tuple indexB = new Tuple(j, k);

        if (map.containsKey(indexA)) {
            a = map.get(indexA);
            System.out.println("matched");
        }
        else {
            for (a = arr[i]; i < j-1 ; i++) {
                a ^= arr[i+1];
            }
            map.put(indexA, a);
        }


        if (map.containsKey(indexB)) {
            b = map.get(indexB);
            System.out.println("matched");
        }
        else {
            for (b = arr[j]; j < k; j++ ) {
                b ^= arr[j+1];
            }
            map.put(indexB, b);
        }

        return a == b;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1443().countTriplets(new int[]{2,3,1,6,7}));
    }
}