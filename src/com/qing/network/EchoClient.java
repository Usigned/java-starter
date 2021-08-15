package com.qing.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket("localhost", 6666);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.printf("connected to Server: %s%n", socket.getRemoteSocketAddress());
            String userInput;
            while ((userInput = input.readLine()) != null) {
                out.println(userInput);
                out.flush();
                System.out.println("resp from server: " + in.readLine());
            }
        }
    }
}
