package exercise;

public class Solution1736 {
    public String maximumTime(String time) {
        char[] timeArray = time.toCharArray();
        if (timeArray[0] == '?')
            timeArray[0] = (timeArray[1] <= '9' && timeArray[1] >= '4') ? '1' : '2';
        if (timeArray[1] == '?')
            timeArray[1] = (timeArray[0] == '2') ? '3' : '9';
        if (timeArray[3] == '?')
            timeArray[3] = '5';
        if (timeArray[4] == '?')
            timeArray[4] = '9';
        return new String(timeArray);
    }

    public static void main(String[] args) {
        System.out.println(new Solution1736().maximumTime("0?:3?"));
    }
}
