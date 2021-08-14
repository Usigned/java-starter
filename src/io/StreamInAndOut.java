package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamInAndOut {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        String filename = "StreamInAndOut.java";
        try (
                Stream<String> input = Files.lines(Paths.get("src", "io", filename)).parallel();
                PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("assets", "StreamInAndOut.txt")))
        ) {
            input.map(String::toUpperCase).forEachOrdered(writer::println);
            System.out.println("DONE");
        }
    }
}
