package com.zy.designpattern.factory;

public class PhoneFactoryDemo {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        Phone xiaomi = phoneFactory.creatPhone("xiaomi");
        String MIPhone = xiaomi.brand();
        System.out.println(MIPhone);
    }
}
