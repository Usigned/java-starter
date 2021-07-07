package exercise;

import java.util.*;
import java.util.stream.Collectors;

public class Solution1418 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        SortedMap<Integer, Map<String, Integer>> tables = new TreeMap<>();
        SortedSet<String> dishes = new TreeSet<>();
        orders.forEach(order->{
            Integer tableNum = Integer.parseInt(order.get(1));
            String dishName = order.get(2);
            dishes.add(dishName);
            Map<String, Integer> freqMap = tables.get(tableNum);
            if (freqMap != null) {
                Integer freq = freqMap.get(dishName);
                freqMap.put(dishName, (freq == null) ? 1 : freq + 1);
            }
            else {
                freqMap = new HashMap<>();
                freqMap.put(dishName, 1);
            }
            tables.put(tableNum, freqMap);
        });
        List<List<String>> res = new ArrayList<>();
        res.add(new ArrayList<>(dishes));
        res.get(0).add("Table");
        tables.forEach((numTable, order) -> {
            List<String> temp = dishes.stream()
                    .map(dishName -> order.get(dishName) == null ? "0" : order.get(dishName).toString())
                    .collect(Collectors.toList());
            temp.add(0, numTable.toString());
            res.add(temp);
        });


        return res;
    }
}
