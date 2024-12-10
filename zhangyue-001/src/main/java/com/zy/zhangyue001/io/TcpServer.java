package com.zy.zhangyue001.io;

import com.fasterxml.jackson.core.JsonPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        System.out.println("服务等待连接。。。。。");

        Socket accept = serverSocket.accept();

        System.out.println("客户端连接：" + serverSocket.getInetAddress());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);

        String message;

        while ((message = bufferedReader.readLine()) != null) {
            System.out.println("发送消息" + message);
            printWriter.println("Echo" + message);
        }
        accept.close();
        serverSocket.close();


    }
}
