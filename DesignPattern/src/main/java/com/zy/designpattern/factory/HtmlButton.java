package com.zy.designpattern.factory;

public class HtmlButton implements Button{
    @Override
    public void render() {
        System.out.println("<button>测试按钮</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says-'Hello World!' ");
    }
}
