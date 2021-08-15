package com.qing.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.printf("server started at: %s%n", serverSocket.getLocalSocketAddress());
        try (
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.printf("connection from: %s%n", socket.getRemoteSocketAddress());
            for (String input = in.readLine(); input != null; input = in.readLine()) {
                out.println(input);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
