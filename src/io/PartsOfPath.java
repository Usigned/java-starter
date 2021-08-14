package io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PartsOfPath {

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        Path p = Paths.get("src", "io", "PartsOfPath.java").toAbsolutePath();
        System.out.println(p);
        System.out.println(Files.exists(p));
    }
}
