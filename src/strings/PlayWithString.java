package strings;

import java.util.Arrays;

public class PlayWithString {
    public static void main(String[] args) {
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        String s = new String(chars);
        System.out.println(s.substring(0,s.length()));
        String test_split = "what is the matter?";
        String[] ss = test_split.split(" ", 3);
        Arrays.stream(ss).forEach(System.out::println);

        String word = "the";
        String is = test_split.substring(test_split.indexOf(word), test_split.indexOf(word) + word.length());
        System.out.println(is);

        System.out.println("---------------------------------");

        String searchMe = "Green Eggs and Ham";
        String findMe = "Eggs a";
        int searchMeLength = searchMe.length();
        int findMeLength = findMe.length();
        boolean foundIt = false;
        for (int i = 0; i < (searchMeLength - findMeLength); i++) {
            if (searchMe.regionMatches(i, findMe, 0, findMeLength)) {
                foundIt = true;
                System.out.println(searchMe.substring(i, i+findMeLength));
                break;
            }
        }
        if (!foundIt) {
            System.out.println("Not found");
        }

        System.out.println("Able was I ere I saw Elba.".length());
    }
}
