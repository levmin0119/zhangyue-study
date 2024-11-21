package com.zy.designpattern.abstractfactory;

public class MIPhone implements Phone {
    @Override
    public String call() {
        return "创建一台MI15PRO";
    }
}
