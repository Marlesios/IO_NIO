package com.example.io_nio;


import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;


import static java.lang.System.out;

public class Server {
    public static void main(String[] args) throws IOException {


        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localHost", 21445));
        out.println("server started");

        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 13);

                while (socketChannel.isConnected()) {
                    int byteCount = socketChannel.read(inputBuffer);
                    if (byteCount == -1) break;

                    final String msg = new String(inputBuffer.array(), 0, byteCount, StandardCharsets.UTF_8);
                    inputBuffer.clear();
                    out.println("полученные данные : " + msg);

                    socketChannel.write(ByteBuffer.wrap((msg.replaceAll("\\s+", "")).getBytes(StandardCharsets.UTF_8)));
                }
            } catch (IOException ex) {
                out.println(ex.getMessage());
            }
        }

    }


}
