package io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PartsOfPath {

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        Path p = Paths.get("src", "io", "PartsOfPath.java").toAbsolutePath();
        System.out.println(p);
        for (int i = 0; i < p.getNameCount(); i++) {
            System.out.println(p.getName(i));
        }
        System.out.println(p.getRoot());
        System.out.println(p.endsWith("PartsOfPath.java"));
        Path pp = p.getParent();
        System.out.println(pp);
        Path np = pp.resolve("afadfa.java");
        System.out.println(np);
        System.out.println(Files.exists(np));
        Path rp = np.relativize(Path.of("..", "interface").toAbsolutePath());
        System.out.println(rp);
    }
}
