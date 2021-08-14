package io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInfo {
    static void show(String id, Object p) {
        System.out.println(id + ": " + p);
    }

    static void info(Path path) {
        show("toString", path);
        show("Exists", Files.exists(path));
        show("RegularFile", Files.isRegularFile(path));
        show("Directory", Files.isDirectory(path));
        show("Absolute", path.isAbsolute());
        show("FileName", path.getFileName());
        show("Parent", path.getParent());
        show("Root", path.getRoot());
        System.out.println("******************");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        info(Paths.get("/", "not-a-pat", "File.txt"));
        Path path = Paths.get("src/io/PathInfo.java");
        info(path);
        Path ap = path.toAbsolutePath();
        info(ap);
        try {
            info(path.toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }
        URI uri = path.toUri();
        System.out.println("URI: " + uri);
        Path puri = Paths.get(uri);
        System.out.println(puri);
        System.out.println(Files.exists(Path.of(uri)));
        File file = ap.toFile();
    }
}
