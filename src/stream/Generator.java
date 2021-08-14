package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator implements Supplier<String> {
    Random rand = new Random(47);
    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public String get() {
        return ""  + letters[rand.nextInt(letters.length)];
    }

    public static void main(String[] args) throws IOException {
        String word = Stream.generate(new Generator())
                .limit(5)
                .collect(Collectors.joining());
        System.out.println(word);
        Files.readAllLines(Paths.get("assets/xanadu.txt"))
                .forEach(System.out::print);
    }
}
