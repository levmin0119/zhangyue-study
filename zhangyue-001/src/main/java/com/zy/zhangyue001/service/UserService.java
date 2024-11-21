package com.zy.zhangyue001.service;


import com.zy.zhangyue001.entity.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new User("ZHANGYUE"), new User("LIJINFANG"));
    }
}
