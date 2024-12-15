package com.zy.zhangyue001.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.zy.zhangyue001.entity.Stu;
import com.zy.zhangyue001.entity.StuExample;
import com.zy.zhangyue001.entity.Student;
import com.zy.zhangyue001.mapper.StuMapper;
import com.zy.zhangyue001.mapper.StudentMapper;
import com.zy.zhangyue001.req.StuRequest;
import com.zy.zhangyue001.req.StudentRequest;
import com.zy.zhangyue001.service.StuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StuServiceImpl implements StuService {

    private static final Logger log = LoggerFactory.getLogger(StuServiceImpl.class);
    @Autowired
    private StuMapper stuMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Stu> getStus(StuRequest stuRequest) {
        StuExample stuExample = new StuExample();
        StuExample.Criteria criteria = stuExample.createCriteria();
        if (!StringUtil.isNullOrEmpty(stuRequest.getName())) {
            criteria.andNameLike("%" + stuRequest.getName() + "%");
        }
        if (!StringUtil.isNullOrEmpty(stuRequest.getSex())) {
            criteria.andSexEqualTo(stuRequest.getSex());
        }
        return stuMapper.selectByExample(stuExample);
    }

    @Transactional()
    @Override
    public int deleteStu(StuRequest stuRequest) {
        if (stuRequest.getId() != null) {
            return stuMapper.deleteByPrimaryKey(stuRequest.getId());
        }
        return 0;
    }

    @CachePut(value = "stu", key = "#student.id")
    @Override
    public void addStu(StudentRequest stuRequest) {
        log.info("name:{},age:{}", stuRequest.getNAME(), stuRequest.getAGE());
        Student stu = new Student();
        BeanUtils.copyProperties(stuRequest, stu);

        log.info("name:{},age:{}", stu.getNAME(), stu.getAGE());
        studentMapper.insert(stu);
    }

    @Cacheable(value = "stu", key = "#student.id")
    @Override
    public Student getStu(int stuId) {
        Student stu = studentMapper.selectByPrimaryKey(stuId);
        return stu;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aa", "11");
        map.put("aa", "22");

        System.out.println(map.get("aa"));

        String aa = "11";
        int i = aa.hashCode();
        System.out.println(i);

        int i1 = map.get("aa").hashCode();
        System.out.println(i1);
        System.out.println(map.get("aa").hashCode());

        //泛型可以在编译时对数据类型进行校验,避免类型转换
        List list= new ArrayList<>();
        list.add("111");
        list.add(222);
        list.add(12.2);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        LinkedList<String> strings = new LinkedList<>();


    }


}
