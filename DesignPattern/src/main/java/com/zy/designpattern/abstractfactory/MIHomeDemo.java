package com.zy.designpattern.abstractfactory;

public class MIHomeDemo {

    public static void main(String[] args) {
        MIHome miHome = new PhoneFactory();

        Phone mi = miHome.createPhone("MI");
        System.out.println(mi.call());
        Phone redMi = miHome.createPhone("RedMI");
        System.out.println(redMi.call());

        MIHome watch = new WatchFactory();
        Watch watch1 = watch.createWatch("Watch");
        System.out.println(watch1.createWatch());
        Watch bracelet = watch.createWatch("bracelet");
        System.out.println(bracelet.createWatch());
    }
}
