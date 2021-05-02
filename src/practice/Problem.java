package practice;

public class Problem {
    static String s;
    static class Inner {
        void testMethod() {
            s = "Set from Inner";
        }
    }
}
