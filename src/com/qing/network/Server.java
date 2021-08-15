package com.qing.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements Runnable {
    ServerSocket serverSocket;
    ExecutorService executorService;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(3);
    }

    @Override
    public void run() {
        System.out.printf("Server started at: %s%n", serverSocket.getLocalSocketAddress());
        int count = 0;

        // create a new task and submit it to executor
        while (true) {
            try {
                executorService.submit(new HandlerWithId(count, serverSocket.accept()));
                System.out.printf("#%d request submit to thread pool%n", ++count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class HandlerWithId implements Runnable{
        final int id;
        final Socket in;

        public HandlerWithId(int id, Socket in) {
            this.id = id;
            this.in = in;
        }


        @Override
        public void run() {
            try (
                    Writer writer = new PrintWriter(in.getOutputStream());
            ) {
                System.out.printf("incoming connection from %s%n", in.getRemoteSocketAddress(), in.getPort());
                writer.write("Hello, I'm the server, nice to meet you.");
                System.out.printf("#%d thread done%n", id);
                System.out.println(in.isClosed());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(in.isClosed());
        }
    }
    public static void main(String[] args) throws IOException {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Server(6666));
    }
}
