package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            System.out.println("Start server...");
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if ("hello".equals(str.substring(10, 15).toLowerCase(Locale.ROOT))) {
                            out.write("Server say: Hello!".getBytes());
                            break;
                        } else if ("exit".equals(str.substring(10, 14).toLowerCase(Locale.ROOT))) {
                            out.write("Server say: Exit!".getBytes());
                            System.out.println("Server stop...");
                            server.close();
                            break;
                        } else {
                            out.write("Server say: What ?".getBytes());
                            break;
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
