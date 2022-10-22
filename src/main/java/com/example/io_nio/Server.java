package com.example.io_nio;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;

public class Server {
    public static void main(String[] args) throws IOException {

        //Здесь использовал blocking потому как для вычислений очень важно что бы вся информация
        //дошла и нужно будет её всё проработать а не кусочки
        ServerSocket serverSocket = new ServerSocket(32001);
        out.println("server started");

        Socket clientSocket = serverSocket.accept();
        out.println("client connected " + clientSocket.getRemoteSocketAddress());


        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            while (true) {

                String msg;
                if ((msg = in.readLine()) != null) {
                    int number = Integer.parseInt(msg);
                    out.println(fib(number));
                }
                if ("end".equals(msg)) {
                    break;
                }
                serverSocket.close();
            }
        }

    }

    public static int fib(int n) {
        if (n == 0 || n == 1)
            return n;
        else if (n == 2)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
