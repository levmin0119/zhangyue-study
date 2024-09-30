package com.zy.zhangyue001.controller;

import com.zy.zhangyue001.entity.Stu;
import com.zy.zhangyue001.entity.StuExample;
import com.zy.zhangyue001.mapper.StuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private StuMapper stuMapper;


    @GetMapping("/name")
    public String hello(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @PostMapping("/saveStu")
    public String saveStu(@RequestBody Stu stu) {

        stuMapper.insert(stu);
        return "success";

    }

    @GetMapping("getStu")
    public Stu getStu() {
        StuExample stuExample = new StuExample();
        StuExample.Criteria criteria = stuExample.createCriteria();
        criteria.andIdEqualTo(1);
        List<Stu> stus =
                stuMapper.selectByExample(stuExample);
        return stus.get(0);
    }
}
