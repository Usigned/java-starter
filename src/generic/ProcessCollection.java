package generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class ProcessCollection {

    public static <T> int count(Collection<T> collection, Predicate<T> counter) {
        return collection.stream().filter(counter).toArray().length;
    }

    public static void main(String[] args) {
        Collection<Integer> integerCollection = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("#Odd Integer in Integer Collection: " + count(integerCollection, new OddPredicate()));

        Collection<String> stringCollection = Arrays.asList("abcdefg", "abcdcba", "dot saw I was tod");
        System.out.println("#Palindromes in String Collection: " + count(stringCollection, new PalindromesPredicate()));

    }
}

class OddPredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer integer) {
        return integer % 2 == 1;
    }
}

class PalindromesPredicate implements Predicate<String> {

    @Override
    public boolean test(String s) {
        StringBuilder builder = new StringBuilder(s);
        return builder.reverse().toString().equals(s);
    }
}






