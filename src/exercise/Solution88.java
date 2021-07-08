package exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Solution88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        for (int i = 0; i < n; i++) {
//            nums1[m+i] = nums2[i];
//        }
//        Arrays.sort(nums1);
        //copy from others, start from the tile
        int i = m-- + --n;
        while (n>=0)
            nums1[i--] = m>=0 && nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
    }


}
