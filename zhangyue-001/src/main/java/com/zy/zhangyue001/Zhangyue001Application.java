package com.zy.zhangyue001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Zhangyue001Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Zhangyue001Application.class, args);
        Object helloController = run.getBean("helloController");

    }

}
