package io;

import java.io.BufferedReader;
// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class CopyCharacters {
    public static void main(String[] args) throws IOException {
        
        // byteCopy();
        line_orientedIO();
    }

    private static void byteCopy() throws IOException {
        try (
            FileReader reader = new FileReader("doc/xanadu.txt");
            FileWriter writer = new FileWriter("assets/charout.txt");
        ) {
            int c, count = 0;
            while ((c = reader.read()) != -1) {
                System.out.println("int value: " + c + ", char value: " + (char)c);
                writer.write(c);
                count ++;
            }
            System.out.println("total count: " + count);
        }
    }

    public static void line_orientedIO() throws IOException {
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader("assets/xanadu.txt"));
            PrintWriter prinWriter = new PrintWriter(new FileWriter("assets/charout-line-oriented.txt"));
        ) {
            String l;
            while ((l = bufferedReader.readLine()) != null) {
                prinWriter.println(l);
            }
        }
    }
}
