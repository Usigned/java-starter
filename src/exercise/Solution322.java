package exercise;

/**
 * Solution322
 */
public class Solution322 {
    public static void main(String[] args) {
        System.out.println(new Solution322().coinChange(new int[]{1, 2, 5}, 11));
    }

    public int coinChange(int[] coins, int amount) {
        int[] mem = new int[amount];
        return extracted(coins, amount, mem);
    }

    private int extracted(int[] coins, int amount, int[] mem) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (mem[amount-1] != 0) {
            return mem[amount-1];
        }
        int min = -1;
        for (int coin : coins) {
            int result = extracted(coins, amount - coin, mem);
            if (result >= 0 && (min == -1 || result < min)) {
                min = result + 1;
            }
        }
        return min;
    }
}