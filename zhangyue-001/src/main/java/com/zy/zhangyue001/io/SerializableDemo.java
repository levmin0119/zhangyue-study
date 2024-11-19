package com.zy.zhangyue001.io;

import java.io.*;

/**
 * 序列化
 */
public class SerializableDemo {

    public static void main(String[] args)  {
        Employee employee = new Employee();
        employee.setName("ZHANGYUE");
        employee.setAddress("HENAN,JIAOZUO,BOAI");
        employee.setSSN(11122333);
        employee.setNumber(101);

        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\zhangyue\\Desktop\\note\\employee.ser");

            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(employee);
            oos.close();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
