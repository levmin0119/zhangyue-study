package com.zy.springdetailedexplanation.service;

import com.zy.springdetailedexplanation.pojo.User;

import java.util.Arrays;
import java.util.List;

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
