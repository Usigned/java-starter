package generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public final class Algorithm {
    public static <T> int findFirst(
            List<T> list, int begin, int end,
            Predicate<T> p)
    {
        for (int i = begin; i < end; i++) {
            if (p.test(list.get(i))) {
                return i;
            }
        }
        return -1;
    }


    public static int gcd(int x, int y) {
        for (int r; (r = x % y) != 0; x = y, y= r) { }
        return y;
    }


    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(3, 4, 6, 8, 11, 15, 28, 32);
        Collection<Integer> c = Arrays.asList(7, 18, 19, 25);

        int i = Algorithm.findFirst(li, 0, li.size(), new RelativelyPrimePredicate(c));

        if (i != -1) {
            System.out.print(li.get(i) + " is relatively prime to ");
            for (Integer k : c)
                System.out.print(k + " ");
            System.out.println();
        }
    }
}

class RelativelyPrimePredicate implements Predicate<Integer> {

    private Collection<Integer> c;

    public RelativelyPrimePredicate(Collection<Integer> c) {
        this.c = c;
    }

    @Override
    public boolean test(Integer x) {
        for (Integer integer : c) {
            if (Algorithm.gcd(x, integer) != 1) {
                return false;
            }
        }
        return c.size() > 0;
    }

}