package com.qing.network;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        try (
                Socket client = new Socket("localhost", 6666);
                Writer writer = new PrintWriter(client.getOutputStream());
                Reader reader = new InputStreamReader(client.getInputStream())
        ) {
            char[] buffer = new char[200];
            while (reader.read(buffer) != -1) {
                System.out.println(String.valueOf(buffer));
            }
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
