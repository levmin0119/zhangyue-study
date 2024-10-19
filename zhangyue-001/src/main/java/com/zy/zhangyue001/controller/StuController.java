package com.zy.zhangyue001.controller;

import com.zy.zhangyue001.entity.Stu;
import com.zy.zhangyue001.req.StuRequest;
import com.zy.zhangyue001.service.StuService;
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
}
