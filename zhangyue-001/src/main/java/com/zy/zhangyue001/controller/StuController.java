package com.zy.zhangyue001.controller;

import com.zy.zhangyue001.entity.Stu;
import com.zy.zhangyue001.entity.Student;
import com.zy.zhangyue001.req.StuRequest;
import com.zy.zhangyue001.req.StudentRequest;
import com.zy.zhangyue001.service.StuService;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stu")
public class StuController {


    @Autowired
    private StuService stuService;


    @PostMapping("/getStus")
    public List<Stu> getStus(@RequestBody StuRequest stuRequest) {
        return stuService.getStus(stuRequest);
    }

    @PostMapping("/deleteById")
    public int deleteById(@RequestBody StuRequest stuRequest) {
        return stuService.deleteStu(stuRequest);
    }


    @PostMapping("/save")
    public void save(@RequestBody StudentRequest stuRequest) {
        stuService.addStu(stuRequest);
    }


    @PostMapping("/getStu")
    public Student getStu(@RequestBody StuRequest stuRequest) {
        return stuService.getStu(stuRequest.getId());
    }

    public static void main(String[] args) {

    }
}
