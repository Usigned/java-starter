package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solution1738
 */
public class Solution1738 {

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m+1][n+1];
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                pre[i][j] = pre[i-1][j] ^ pre[i][j-1] ^ pre[i-1][j-1] ^ matrix[i][j];
                list.add(pre[i][j]);
            }
        }

        // Collections.sort(list, new Comparator<Integer>(){

        //     @Override
        //     public int compare(Integer o1, Integer o2) {
        //         return o2 - o1;
        //     }
        // });

        Collections.sort(list, (n1, n2) -> {return (n1 - n2);});
        return list.get(k-1);
    }
}