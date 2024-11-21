package com.zy.designpattern.factory;

/**
 * 手机工厂
 */
public class PhoneFactory {

    public Phone creatPhone(String type) {
        if (type.equals("xiaomi")) {
            return new MIPhone();
        }
        if (type.equals("apple")) {
            return new Iphone();
        }
        return null;
    }
}
