package com.zy.zhangyue001.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 复制算法
 * 复制算法是将一块可用的内存空间分为两个大小相等的内存空间，
 * 每次只是用一个内存区域，当一个内存用完的时候，将当前的内存中的存活的对象复制到另一个内存中，
 * 然后将原来内存中剩下的对象清除，反复执行这个过程，可以避免内存碎片的问题。
 */
public class CopyingGC {

    static class Object {
        int size;

        public Object(int size) {
            this.size = size;
        }
    }

    public static void main(String[] args) {
        // 初始化内存空间大小
        int heapSize = 20;

        // 初始化两个Eden空间和两个Survivor空间
        Object[][] heap = new Object[heapSize][2];

        // 初始化对象列表
        List<Object> objects = new ArrayList<>();

        // 分配内存空间并将对象添加到列表中
        for (int i = 0; i < heapSize; i++) {
            Object obj = new Object(i);
            objects.add(obj);

            // 将对象分配到Eden区域中
            heap[i][0] = obj;
        }

        // 清空Eden区域和Survivor区域
        for (Object[] block : heap) {
            Arrays.fill(block, null);
        }

        // 触发一次垃圾回收操作
        gc(objects, heap);

        // 遍历所有对象并打印尚未被清除的对象
        System.out.println("Remaining Objects:");
        for (Object obj : objects) {
            if (obj != null) {
                System.out.println(obj);
            }
        }
    }

    private static void gc(List<Object> objects, Object[][] heap) {
        // 将存活的对象从Eden区域复制到Survivor区域
        for (int i = 0; i < objects.size(); i++) {
            Object obj = objects.get(i);

            if (obj != null) {
                int age = obj.size / 10;
                if (age < 2) {
                    // 将年龄小于2的对象复制到Survivor0区域中
                    allocate(heap, 0, obj);
                } else {
                    // 将年龄大于等于2的对象复制到Survivor1区域中
                    allocate(heap, 1, obj);
                }
            }
        }

        // 清空Eden区域和上一个Survivor区域
        for (int i = 0; i < heap.length; i++) {
            Object obj = heap[i][0];
            if (obj != null) {
                objects.remove(obj);
            }
            heap[i][0] = null;

            obj = heap[i][1];
            if (obj != null && obj.size >= 20) {
                // 将年龄达到阈值的对象转移到老年代中
                objects.remove(obj);
                heap[i][1] = null;
            }
        }

        // 将下一个Survivor区域和当前Survivor区域位置进行交换
        Object[][] temp = new Object[heap.length][2];
        for (int i = 0; i < heap.length; i++) {
            temp[i][0] = heap[i][1];
            temp[i][1] = heap[i][0];
        }
        heap = temp;
    }

    private static void allocate(Object[][] heap, int block, Object obj) {
        // 随机分配到Survivor区域中的任意一个块
        Random r = new Random();
        int index = r.nextInt(heap.length);
        heap[index][block] = obj;
    }
}

