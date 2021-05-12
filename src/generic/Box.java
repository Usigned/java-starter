package generic;

public class Box <T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println(u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        integerBox.setT(10);

        integerBox.inspect(123123123.121212);
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T t : anArray) {
            if (t.compareTo(elem) > 0) {
                count++;
            }
        }
        return count;
    }
}
