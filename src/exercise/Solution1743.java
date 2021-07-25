package exercise;

import java.util.*;

public class Solution1743 {

    public static void main(String[] args) {
        int[][] in = {{2, 1}, {3, 4}, {3, 2}};
        Arrays.stream(new Solution1743().restoreArray(in)).forEach(System.out::println);
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, Set<Integer>> hash = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            int x = adjacentPair[0];
            int y = adjacentPair[1];
            hash.putIfAbsent(x, new HashSet<>());
            hash.putIfAbsent(y, new HashSet<>());
            hash.get(x).add(y);
            hash.get(y).add(x);
        }
        int[] res = new int[adjacentPairs.length+1];
        for (Map.Entry<Integer, Set<Integer>> entry : hash.entrySet()) {
            if (entry.getValue().size() == 1) {
                res[0] = entry.getKey();
                break;
            }
        }
        for (int i = 1; i < res.length; i++) {
            Set<Integer> values = hash.get(res[i-1]);
            if (values.size() == 1)
                res[i] = (int) hash.get(res[i-1]).toArray()[0];
            else {
                int dup = res[i-2];
                res[i] = (int) hash.get(res[i - 1]).stream().filter(p -> p != dup).toArray()[0];
            }
        }
        return res;
    }
}
