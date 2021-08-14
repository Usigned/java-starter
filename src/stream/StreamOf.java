package stream;


import java.util.Random;
import java.util.stream.Stream;

public class StreamOf {
    public static void main(String[] args) {
        new Random(47)
                .ints(5, 14)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
        Stream.of(1, 2, 3, 5.111).forEach(System.out::println);
    }
}
