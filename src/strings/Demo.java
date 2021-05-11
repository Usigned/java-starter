package strings;

public class Demo {
    public static void main(String[] args) {
        /*
        use string builder to easily reverse a string.
        if we used string, we'll have to write a lot more code of for-loop.
         */
        String palindrome = "Dot saw I was Tod";

        StringBuilder builder = new StringBuilder(palindrome);

        builder.reverse();
        System.out.println(builder);

    }
}
