package collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Exercise {
    public static void main(String[] args) {
        // randomPring(args);
        // findDups(args);
        List<String> l = Arrays.asList(" a a ff", "a b b  ");
        trim(l);
        l.stream().forEach(e->System.out.println(e));
    }

    static void randomPring(String[] args) {
        Collections.shuffle(Arrays.asList(args));
        Arrays.asList(args).forEach((e)-> System.out.println(e));
    }

    static void findDups(String[] args) {
        SortedSet<String> s = new TreeSet<String>((o1, o2)->{
            return o1.hashCode() - o2.hashCode();
        });
        Arrays.asList(args).forEach((e)->s.add(e));
        s.stream().forEachOrdered((e)->System.out.println(e));
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
