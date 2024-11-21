package com.zy.designpattern.abstractfactory;

/**
 * 手机工厂只需要实现创建手机的功能
 */
public class PhoneFactory extends MIHome {
    @Override
    public Phone createPhone(String name) {
        if (name.equals("MI")) {
            return new MIPhone();
        }
        if (name.equals("RedMI")) {
            return new RedMI();
        }
        return null;
    }

    @Override
    public Watch createWatch(String name) {
        return null;
    }
}
