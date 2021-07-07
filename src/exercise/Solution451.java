package exercise;

import java.util.*;
import java.util.stream.Collectors;

public class Solution451 {
    public static void main(String[] args) {
        System.out.println(new Solution451().frequencySort("tree"));
    }

    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer freq = freqMap.get(c);
            freqMap.put(c, (freq == null) ? 1 : freq+1);
        }
        StringBuilder res = new StringBuilder();
        Map<Character, Integer> sortedFreqMap = new LinkedHashMap<>();
        freqMap.entrySet().stream().sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                .collect(Collectors.toList()).forEach(p->sortedFreqMap.put(p.getKey(), p.getValue()));
        for (Map.Entry<Character, Integer> p : sortedFreqMap.entrySet()) {
            for (int i = 0; i < p.getValue(); i++) {
                res.append(p.getKey());
            }
        }
        return res.toString();
    }

}
