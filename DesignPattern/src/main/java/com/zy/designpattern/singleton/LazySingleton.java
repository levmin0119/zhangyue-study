package com.zy.designpattern.singleton;

/**
 * 单例模式保证一个类在整个系统中同一时刻只有一个实例存在
 * 饿汉模式
 */
public class LazySingleton {

    private static LazySingleton instance;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
