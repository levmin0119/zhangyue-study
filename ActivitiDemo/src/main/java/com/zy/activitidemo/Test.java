package com.zy.activitidemo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class Test {

    public static void main(String[] args) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(defaultProcessEngine);
    }
}
