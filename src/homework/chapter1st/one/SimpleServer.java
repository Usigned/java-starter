package homework.chapter1st.one;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8666);
        try (
                Socket connectionSocket = serverSocket.accept();
                Writer writer = new PrintWriter(connectionSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()))
        ) {
            System.out.print("connection in");
            StringBuilder request = new StringBuilder();
            for (String in = reader.readLine(); !in.equals(""); in = reader.readLine())
                request.append(in);
            Path filePath = extractFilePath(request.toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            if (Files.isRegularFile(filePath)) {
                String header = "HTTP/1.1 200 OK\r\n";
                header += "Connection: close\r\n";
                header += "Date: " + dateFormat.format(new Date()) + "\r\n";
                header += "\r\n";
                String data = Files.readString(filePath);
                System.out.println(header + data);
                writer.write(header + data);
                System.out.printf("Got it %s", filePath);
            } else {
                String header = "HTTP/1.1 404 Not Found\r\n";
                header += "Connection: close\r\n";
                header += "Date: " + dateFormat.format(new Date()) + "\r\n\r\n";
                writer.write(header);
                System.out.printf("Not found %s", filePath);
            }
            writer.flush();
        }
    }

    private static Path extractFilePath(String request) {
        String file = request.split(" ")[1];
        System.out.println(file);
        return Paths.get(System.getProperty("user.dir"), file);
    }
}
