package exercise;

/**
 * Solution518
 */
public class Solution518 {
    public static void main(String[] args) {
        System.out.println(new Solution518().change(500, new int[]{3,5,7,8,9,10,11}));
    }

    public int change(int amount, int[] coins) {
        return extracted(amount, coins, 0);
    }

    private int extracted(int amount, int[] coins, int coinIndex) {

        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        if (coinIndex >= coins.length) {
            return 0;
        }
        int count = 0;
        int coin = coins[coinIndex];
        for (int i = 0; i <= amount / coin; i++) {
            count += extracted(amount - (coin * i), coins, coinIndex + 1);
        }
        return count;
    }
}