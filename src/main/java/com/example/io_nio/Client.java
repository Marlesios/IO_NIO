package com.example.io_nio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 32001);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String msg;
            String reply;
            do {
                System.out.println("Enter the number to count the equation ");
                msg = scanner.nextLine();
                out.println(msg);
                reply = in.readLine();
                System.out.println("Результат:  " + reply);
            } while (!"end".equals(msg));
        }
    }
}
