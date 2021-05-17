package exercise;

// import java.lang.reflect.Array;
import java.util.Arrays;
// import java.util.List;

public class StringExe {
    public static void main(String[] args) {
        String s = "Able was I ere I saw Elba.";
        StringBuilder sb = new StringBuilder(s);
        System.out.println(s.length());
        System.out.println(sb.capacity() + ", " + sb.length());

        String hannah = "Did Hannah see bees? Hannah did.";
        System.out.println(hannah.length());
        System.out.println(hannah.charAt(12));
        System.out.println(hannah.charAt(hannah.indexOf('b')));

        System.out.println(areAnagrams("abcd", "dcbae"));
    }

    public static boolean areAnagrams(String s1, String s2) {
        String sorted_s1 = sort(removeJunk(s1));
        String sorted_s2 = sort(removeJunk(s2));
        return sorted_s1.equals(sorted_s2);
    }

    static String removeJunk(String s) {
        StringBuilder result = new StringBuilder(s);
        for (int i = 0; i < result.length(); i++) {
            if (!Character.isLetter(result.charAt(i))) {
                result.deleteCharAt(i);
            }
        }
        return result.toString();
    }

    static String sort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
