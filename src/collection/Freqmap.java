package collection;

// import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Freqmap {
    public static void main(String[] args) {
        Map<String, Integer> freqMap = new TreeMap<>();

        for (String arg : args) {
            Integer freq = freqMap.get(arg);
            freqMap.put(arg, (freq == null)? 1 : freq+1);
        }

        System.out.println(freqMap.size() + " distinct words");
        System.out.println(freqMap);
    }
}
