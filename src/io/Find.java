package io;

import java.io.IOException;
import java.nio.file.*;

public class Find {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("src");
        System.out.println(Files.isDirectory(dir));
        if (Files.isDirectory(dir)) {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.{java}");
            Files.walk(dir).filter(matcher::matches).forEach(System.out::println);
        }
        else {
            System.out.println(Files.isDirectory(dir));
        }
    }
}
