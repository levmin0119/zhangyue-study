package com.zy.zhangyue001.demo;

public class ThreadDemo extends Thread{
    public void run() {
        System.out.println("创建线程1");
    }

    public static void main(String[] args) {
        new ThreadDemo().start();

    }

}
