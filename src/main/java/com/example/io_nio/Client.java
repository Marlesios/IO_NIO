package com.example.io_nio;

import java.io.*;
import java.net.InetSocketAddress;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1",21445);
        final SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(socketAddress);

        try(Scanner scan = new Scanner(System.in)){
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 <<13);
            String msg;
                while(true){
                    System.out.println("Введите данные для расчета...");
                    msg = scan.nextLine();
                    if("end".equals(msg)) break;

                    socketChannel.write(ByteBuffer.wrap(
                            msg.getBytes(StandardCharsets.UTF_8)));
                    Thread.sleep(1000);

                    int byteCount =socketChannel.read(inputBuffer);
                    inputBuffer.clear();
                    System.out.println(new String(inputBuffer.array(),0,byteCount,StandardCharsets.UTF_8));
                }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            socketChannel.close();
        }
    }
}
