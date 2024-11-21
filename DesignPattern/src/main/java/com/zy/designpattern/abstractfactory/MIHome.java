package com.zy.designpattern.abstractfactory;

/**
 * 利用小米之家来代替抽象工厂
 */
public abstract class MIHome {
    /**
     * 创建手机
     *
     * @return
     */
    public abstract Phone createPhone(String name);

    /**
     * 创建手表
     *
     * @return
     */
    public abstract Watch createWatch(String name);
}
