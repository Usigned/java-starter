package Interface;

public class DefaultMethodTest implements Default1, Default2{
    /*
    When try to implements two interfaces that have the same signature of default method
    and you don't override them,
    a compile time error will occur.
     */
    @Override
    public void test() {
        Default1.super.test();
        Default2.super.test();
    }
}
