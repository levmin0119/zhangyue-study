package com.zy.zhangyue001;


import com.zy.zhangyue001.entity.User;
import com.zy.zhangyue001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppDemo implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {

        SpringApplication.run(AppDemo.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (User user : userService.getUsers()) {
            System.out.println(user.getName());
        }

    }
}
