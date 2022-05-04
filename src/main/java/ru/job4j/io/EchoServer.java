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
                    /*
                        Тут читается весь входной поток
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine())
                     */
                    String str = in.readLine();
                    if (str != null && !str.isEmpty()) {
                        System.out.println(str);
                        if ("bye".equals(str.substring(10, 13).toLowerCase(Locale.ROOT))) {
                            out.write("Server say: Bye!!".getBytes());
                            server.close();
                            System.out.println("Server stop...");
                            break;
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
