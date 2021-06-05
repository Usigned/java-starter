package exercise;

public class Solution1744 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        boolean[] result = new boolean[queries.length];
        long[] sum = new long[candiesCount.length+1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] + candiesCount[i-1];
        }

        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0];
            int date = queries[i][1];
            int cap = queries[i][2];
            
            long low = sum[type] +1;
            long up = sum[type+1];
            long max = (date + 1) * cap;
            long min = date + 1;
            result[i] = ((up >= min) && (low<= max));
        }
        return result;
    }
}
