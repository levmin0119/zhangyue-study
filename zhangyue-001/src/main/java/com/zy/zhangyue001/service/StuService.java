package com.zy.zhangyue001.service;

import com.zy.zhangyue001.entity.Stu;
import com.zy.zhangyue001.req.StuRequest;

import java.util.List;

public interface StuService {

    List<Stu> getStus(StuRequest stuRequest);
}
