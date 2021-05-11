package generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wildcard {
    public static void main(String[] args) {
        List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        List<String> stringList = Arrays.asList("131312", "23131232");
        process(list);
        _process(list);
    }

    public static void process(List<? extends Number> list) {
        double result = 0;
        for (Number number : list) {
            result += number.doubleValue();
        }
        System.out.println(result);
    }

    public static <T extends Number> void _process(List<T> list) {
        double result = 0;
        for (T t : list) {
            result += t.doubleValue();
        }
        System.out.println(result);
    }

}
