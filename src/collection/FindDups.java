package collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDups {
    public static void main(String[] args) {
        findDups(args);
        findDupsOld(args);
        findDups2(args);
        Arrays.asList(args).stream().collect(Collectors.toList()).subList(0, 2).forEach((e)-> System.out.println(e));
    }

    private static void findDups(String[] args) {
        Set<String> distinctWords = Arrays.asList(args).stream().collect(Collectors.toSet());
        System.out.println(distinctWords.size() + " distinct words: " + distinctWords); 
    }

    private static void findDupsOld(String[] args) {
        Set<String> s = new HashSet<>();
        for (String arg : args) {
            s.add(arg);
        }
        System.out.println(s.size() + " words: " + s);
    }

    private static void findDups2(String[] args) {
        Set<String> uniques = new HashSet<>();
        Set<String> dups = new HashSet<>();

        for (String arg: args) {
            if (!uniques.add(arg)) {
                dups.add(arg);
            }
        }
        uniques.removeAll(dups);

        System.out.println(uniques.size() + " uniques: " + uniques);
        System.out.println(dups.size() + " dups: " + dups);
    }
}
