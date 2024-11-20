package com.zy.zhangyue001.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO，服务端创建连接
 */
public class MyServer {

    private int size = 1024;
    private ServerSocketChannel serverSocketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private int remoteClientNum = 0;

    /**
     * 连接
     *
     * @param port
     */
    public MyServer(int port) {
        try {
            initChannel(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * 初始化通道channel
     *
     * @param port
     * @throws IOException
     */
    public void initChannel(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        //非阻塞状态
        serverSocketChannel.configureBlocking(false);
        //监听端口 服务的端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("监听的端口为：" + port);
        selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        byteBuffer = ByteBuffer.allocate(size);
    }

    private void listen() throws IOException {
        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //如果处于就绪状态就开始接收客户端的连接
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    //channel接收连接
                    SocketChannel accept = server.accept();
                    //TODO channel注册
                    registerChannel(selector, accept, SelectionKey.OP_ACCEPT);

                    //远程客户端的连接数统计
                    remoteClientNum++;
                    System.out.println("在线数" + remoteClientNum);

                    //发送连接西悉尼
                    write(accept, "hello client".getBytes());
                }
                //如果通道已经处于读就绪状态，就直接读通道上的数据
                if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        //从通道中读取数据到缓冲区
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            //写模式变为读模式
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private void write(SocketChannel socketChannel, byte[] bytes) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(bytes);
        //写模式变为读模式
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

    private void registerChannel(Selector selector, SocketChannel socketChannel, int opAccept) throws IOException {
        if (socketChannel == null) {
            return;
        }

        socketChannel.configureBlocking(false);
        socketChannel.register(selector, opAccept);
    }

    public static void main(String[] args) {
        try {
            MyServer myServer = new MyServer(8080);
            myServer.listen();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
