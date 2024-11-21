package com.zy.designpattern.abstractfactory;

public class MIWatch implements Watch{
    @Override
    public String createWatch() {
        return "创建一块小米手表4";
    }
}
