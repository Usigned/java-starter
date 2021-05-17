package annotation;


@ClassPreamble(
        author = "LZQ",
        date = "2021/5/5",
        reviewers = {"LZQ"}
)

public class TestAnnotation {

    @Deprecated
    public static void print() {
        // @NonNull String str;
        System.out.println("test deprecated annotation");
    }

    public static void main(String[] args) {
        print();
    }
}
