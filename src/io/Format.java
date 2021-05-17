package io;

public class Format {
    public static void main(String[] args) {
        int i = 2;
        double r = Math.sqrt(i);

        System.out.print("Square root of ");
        System.out.print(i);
        System.out.print(" is ");
        System.out.print(r);
        System.out.println(".");

        i = 5;
        r = Math.sqrt(i);
        System.out.println("Square root of " + i + " is " + r + ".");

        i = 2;
        r = Math.sqrt(i);

        System.out.format("The square root of %d is %f.%n", i, r);
        
        // Begin Format Specifier: %
        // Argument Index: <
        // Flags: +0
        // Width. Precision: 20.10f
        // Conversion: %n
        System.out.format("%f, %<+020.10f %n", Math.PI);
    }
}
