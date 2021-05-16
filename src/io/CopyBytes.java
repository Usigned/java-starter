package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        // try-with-resources statement can have no catch or finally
        try (
            FileInputStream in = new FileInputStream("assets/xanadu.txt");
            FileOutputStream out = new FileOutputStream("assets/outagain.txt");
        ) {
            int c, count = 0;
            while ((c = in.read()) != -1) {
                out.write(c);
                System.out.println(c + " " + (char)c);
                count ++;
            }
            System.out.println("total count: " + count);
            System.out.println("Finish copy file");
        }
        // } catch (IOException e) {
        //     throw new IOException("Throw io exception from main method", e);
        // }
    }
}
