package Interface;

public interface Default1 {
    int var = 0;

    default void test() {
        System.out.println("1111");
    }
}
