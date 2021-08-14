package io;

import java.io.*;
import java.nio.file.*;
import java.sql.Time;
import java.util.Timer;
import java.util.stream.Stream;

public class CopyTo {
    public static void copyTo(Path src, Path dist) {
        if (Files.exists(src)) {
            try (
                    InputStream in = new FileInputStream(src.toFile())
            ) {
                long start = System.currentTimeMillis();
                System.out.println(src.getFileName() + ": " + Files.size(src) + " bytes");
                byte[] buffer = new byte[1000000000];
                for (int i = 1; in.read(buffer) != -1; i++) {
                    if (i % 500 == 1)
                        System.out.print("#");
                    Files.write(dist, buffer, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
                System.out.println("\nDONE");
                System.out.printf("Duration: %d", System.currentTimeMillis()-start);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println(src + " not exist");
    }

    public static void main(String[] args) {
        copyTo(Paths.get("C:\\Users\\1\\OneDrive", "Sommerville-Software-Engineering-10ed.pdf"), Paths.get("assets", "copy"));
    }
}
