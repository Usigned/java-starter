package exercise;

public class Solution477 {
    
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1 ; j < nums.length; j++) {
                sum += hammingDistance(nums[i], nums[j]);
            }
        }
        return sum;
    }

    public int hammingDistance(int num1, int num2) {
        int diff = Integer.bitCount(num1 ^ num2);
        return diff;
    }

    public static void main(String[] args) {
        System.out.println(new Solution477().totalHammingDistance(new int[]{4, 14, 2}));
    }
}
