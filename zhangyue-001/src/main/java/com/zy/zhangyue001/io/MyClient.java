package com.zy.zhangyue001.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyClient {

    private int size = 1024;
    private ByteBuffer buffer;
    private SocketChannel socketChannel;

    public void connectServer() throws IOException {

        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        buffer = ByteBuffer.allocate(size);
        socketChannel.configureBlocking(false);
        recevie();


    }

    private void recevie() throws IOException {
        while (true){
            buffer.clear();
            int count;
            while ((count = socketChannel.read(buffer))>0){
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.println((char)buffer.get());
                }
                send2Server("你好！".getBytes());
                buffer.clear();
            }
        }
    }

    private void send2Server(byte[] bytes) throws IOException {
        buffer.clear();
        buffer.put(bytes);
        buffer.flip();
        socketChannel.write(buffer);
    }

    public static void main(String[] args) throws IOException {
        new MyClient().connectServer();
    }
}
