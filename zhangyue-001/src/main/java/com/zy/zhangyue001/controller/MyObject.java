package com.zy.zhangyue001.controller;

/**
 * 对象可达到 或被引用
 */
public class MyObject {

    private String objectName;

    private MyObject refrence;

    public MyObject(String objectName) {
        this.objectName = objectName;
    }

    public MyObject(String objectName, MyObject refrence) {
        this.objectName = objectName;
        this.refrence = refrence;
    }


    public static void main(String[] args) {
        MyObject a = new MyObject("a");
        MyObject b = new MyObject("b");
        MyObject c = new MyObject("c");

        a.refrence = b;
        b.refrence = c;

        new MyObject("d",new MyObject("e"));
    }
}
