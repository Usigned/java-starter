package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Scan {
    public static void main(String[] args) throws IOException {
        try (
            Scanner s = new Scanner(new BufferedReader(new FileReader("assets/xanadu.txt")));
        ) {
            while (s.hasNext()) {
                System.out.println(s.next());
            }
        }
    }
}
