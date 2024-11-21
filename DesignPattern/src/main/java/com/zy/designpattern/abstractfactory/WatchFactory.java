package com.zy.designpattern.abstractfactory;

public class WatchFactory extends MIHome {
    @Override
    public MIPhone createPhone(String name) {
        return null;
    }

    @Override
    public Watch createWatch(String name) {
        if (name.equals("Watch")) {
            return new MIWatch();
        }
        if (name.equals("bracelet")) {
            return new Bracelet();
        }
        return null;
    }
}
