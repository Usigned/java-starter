package env;

import java.util.Map;
import java.util.Properties;

public class EnvMap {
    public static void main(String[] args) throws InterruptedException {
//        Map<String, String> env = System.getenv();
//        System.out.println("ENV");
//        for (Map.Entry<String, String> entry : env.entrySet()) {
//            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());
//        }
        long start = System.currentTimeMillis();
        System.getProperties().entrySet().forEach(System.out::println);
        Thread.sleep(100);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
