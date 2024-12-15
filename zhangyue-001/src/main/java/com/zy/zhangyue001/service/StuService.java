package com.zy.zhangyue001.service;

import com.zy.zhangyue001.entity.Stu;
import com.zy.zhangyue001.entity.Student;
import com.zy.zhangyue001.req.StuRequest;
import com.zy.zhangyue001.req.StudentRequest;

import java.util.List;

public interface StuService {

    List<Stu> getStus(StuRequest stuRequest);

    int deleteStu(StuRequest stuRequest);

    void addStu(StudentRequest stuRequest);

    Student getStu(int stuId);

}
