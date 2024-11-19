package com.zy.zhangyue001.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DisSerializableDemo {

    public static void main(String[] args) {
        Employee o = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zhangyue\\Desktop\\note\\employee.ser");

            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            o = (Employee) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

        System.out.println(o.getName() + "-" + o.getSSN() + "-" + o.getNumber() + "-" + o.getAddress());
    }
}
