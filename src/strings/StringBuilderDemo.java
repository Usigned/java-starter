package strings;

public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        System.out.println(builder.length());
        System.out.println(builder.capacity());
        builder.append("this is a string longer than 16 elements");
        System.out.println(builder.length() + ", " + builder.capacity());
        builder.ensureCapacity(10100101);
        System.out.println(builder.length() + ", " + builder.capacity());
        builder.append(Double.NaN);
        System.out.println(builder.reverse() + ", " + builder.length());
    }
}
