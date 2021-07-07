package exercise;

public class Solution1771 {
    public static void main(String[] args) {
        System.out.println(new Solution1771().countPairs(new int[]
                {2160,1936,3,29,27,5,2503,1593,2,0,16,0,3860,28908,6,2,15,49,6246,1946,23,105,7996,196,0,2,55,457,5,3,924,7268,16,48,4,0,12,116,2628,1468}));
//        System.out.println(new Solution1771().isPowerOfTwo(4));
    }

    public int countPairs(int[] deliciousness) {
        int count = 0;
        for (int i = 0; i < deliciousness.length; i++) {
            for (int j = i+1; j < deliciousness.length; j++) {
                if (isPowerOfTwo(deliciousness[i] + deliciousness[j])) {
                    count ++;
                }
            }
        }
        return count % (int) (1e9 + 7);
    }
    private boolean isPowerOfTwo(int input) {
        if (input == 1) {
            return true;
        }
        else {
            if (input % 2 != 0 || input == 0) {
                return false;
            }
            else
                return isPowerOfTwo(input / 2);
        }
    }
}
