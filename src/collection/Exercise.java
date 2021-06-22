package collection;

import java.util.*;

public class Exercise {
    public static void main(String[] args) {
        // randomPrint(args);
        // findDups(args);
        List<String> l = Arrays.asList(" a a ff", "a b b  ");
        trim(l);
        l.forEach(System.out::println);
    }

    static void randomPrint(String[] args) {
        Collections.shuffle(Arrays.asList(args));
        Arrays.asList(args).forEach(System.out::println);
    }

    static void findDups(String[] args) {
        SortedSet<String> s = new TreeSet<>(Comparator.comparingInt(String::hashCode));
        s.addAll(Arrays.asList(args));
        s.forEach(System.out::println);
    }

    static void trim(List<String> args) {
        for (ListIterator<String> iter = args.listIterator(); iter.hasNext(); ) {
            iter.set(iter.next().trim());
        }
    }

    static <K> Map<K, Integer> count(List<K> names) {
        Map<K, Integer> map = new HashMap<>();
        names.forEach((name)->{
            Integer freq = map.get(name);
            map.put(name, (freq == null) ? 1 : freq + 1);
        });
        return map;
    }
}
