package com.zy.zhangyue001.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        printWriter.println("Hello World");
        System.out.println("server response: " + bufferedReader.readLine());

        socket.close();


    }
}
