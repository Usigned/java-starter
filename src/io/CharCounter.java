package io;

// import java.io.BufferedInputStream;
import java.io.BufferedReader;
// import java.io.DataInputStream;
// import java.io.EOFException;
// import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CharCounter {
    public static void main(String[] args) throws IOException {
        String file = "assets/xanadu.txt";
        char target = args[0].charAt(0);
        readerCounter(target, file);
        bufferedReaderCounter(target, file);
        // dataStreamCounter(target, file);
    }

    private static void readerCounter(char target, String file) throws IOException {
        int count = 0;

        try (
            FileReader in = new FileReader(file);
        ) {        
            for (int temp = in.read(); temp != -1; temp = in.read()) {
                if (temp == target) {
                    count ++;
                }
            }
        }
        System.out.format("Total count of %c is %d%n", target, count);
    }

    private static void bufferedReaderCounter(char target, String file) throws IOException {
        int count = 0;

        try (
            BufferedReader in = Files.newBufferedReader(Paths.get(file));  
        ) {
            for (String l = in.readLine(); l != null; l = in.readLine()) {
                for (int i = 0; i < l.length(); i++) {
                    if (target == l.charAt(i)) {
                        count++;
                    }
                }
            }  
        } 
        System.out.format("Total count of %c is %d%n", target, count);
    }

    // private static void dataStreamCounter(char target, String file) throws IOException {
    //     int count = 0;

    //     try (
    //         DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
    //     ) {
    //         while (true) {
    //             String l = in.readUTF();
    //             System.out.println(l);
    //             for (int i = 0; i < l.length(); i++) {
    //                 if (target == l.charAt(i)) {
    //                     count++;
    //                 }
    //             }
    //         } 
    //     } catch (EOFException e) {
    //         System.out.format("Total count of %c is %d", target, count);
    //     }
    // }
}
