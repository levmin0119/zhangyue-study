package com.zy.zhangyue001.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * jvm 中垃圾回收的标记清除
 * jvm 在对象创建的时候，会同时创建一个计数器，当前对象被引用，计数器加1，对象没有被引用，对象减1，当计数器为0的时候
 * jvm会将当前对象视为垃圾，会进行回收
 * <p>
 * 算法实现：1、初始化标记位向量，将所有对象标记为不用
 * 2、从根集开始遍历，标记所有可访问的对象，将其标记为已使用
 * 3、遍历整个堆空间，将未被标记的对象视为垃圾，并回收该对象所占用的内存
 * 4、对内存空间进行重新组合，减少空间碎片化
 */
public class MarkAndSweepDemo {

    private static List<MyObj> objs = new ArrayList<MyObj>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            objs.add(new MyObj(i, "Object " + i));
        }
        markAndSweep();

        System.out.println("Remaining Objects:");
        for (MyObj obj : objs) {
            if (obj!=null){
                System.out.println(obj.toString());
            }
        }
    }

    private static void markAndSweep() {
        //初始化标记位向量
        boolean[] booleans = new boolean[objs.size()];

        //标记所有可以访问的对象
        for (int i = 0; i < objs.size(); i++) {
            MyObj obj = objs.get(i);
            if (obj != null) {
                marked(i, booleans);
            }
        }

        for (int i = 0; i < objs.size(); i++) {
            if (!booleans[i]) {
                objs.set(i, null);
            }
        }

        while (objs.contains(null)) {
            objs.remove(null);
        }
    }

    private static void marked(int index, boolean[] booleans) {
        if (booleans[index]) {
            return;
        }
        booleans[index] = true;

        MyObj myObj = objs.get(index);
        for (int i = 0; i < objs.size(); i++) {
            if (objs.get(i) != null && myObj.isRelated(objs.get(i))) {
                marked(i, booleans);
            }
        }
    }


    static class MyObj {
        /**
         * 对象的名称
         */
        private String objName;
        /**
         * 对象标记编号
         */
        private int objNum;

        public MyObj(int objNum, String objName) {
            this.objNum = objNum;
            this.objName = objName;
        }

        public boolean isRelated(MyObj obj) {
            return objNum == obj.objNum - 1 || objNum == obj.objNum + 1;
        }

        @Override
        public String toString() {
            return objName + " " + objNum;
        }
    }
}
