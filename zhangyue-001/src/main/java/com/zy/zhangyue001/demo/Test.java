package com.zy.zhangyue001.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
//        Person person = new Person("15093307913","河南省焦作市",23,"张三");

        Class<?> person = Class.forName("com.zy.zhangyue001.demo.Person");
        Class clazz = person.getClass();

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName());
        }


    }


}
