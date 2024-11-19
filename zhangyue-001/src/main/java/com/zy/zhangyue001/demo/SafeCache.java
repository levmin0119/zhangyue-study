package com.zy.zhangyue001.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class SafeCache {

    private final Map<String, Object> cache = new HashMap<String, Object>();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //读锁
    private final Lock readLock = readWriteLock.readLock();
    //写锁
    private final Lock writeLock = readWriteLock.writeLock();

    public Object read(String key) {
        //读数据枷锁
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            //释放锁
            readLock.unlock();
        }
    }


    public void write(String key, Object value) {
        writeLock.lock();
        try {
            cache.put(key, value);

        } finally {
            writeLock.unlock();
        }
    }

}
