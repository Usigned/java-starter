package exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public static void main(String[] args) {
        int[] res = new Solution1().twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int re : res) {
            System.out.println(re);
        }
    }
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                res[0] = i;
                res[i] = hashMap.get(nums[i]);
                break;
            }
            else {
                hashMap.put(target-nums[i], i);
            }
        }
        return res;
    }
}
