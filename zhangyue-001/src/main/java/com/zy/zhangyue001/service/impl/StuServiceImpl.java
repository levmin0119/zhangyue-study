package com.zy.zhangyue001.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.zy.zhangyue001.entity.Stu;
import com.zy.zhangyue001.entity.StuExample;
import com.zy.zhangyue001.mapper.StuMapper;
import com.zy.zhangyue001.req.StuRequest;
import com.zy.zhangyue001.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public List<Stu> getStus(StuRequest stuRequest) {
        StuExample stuExample = new StuExample();
        StuExample.Criteria criteria = stuExample.createCriteria();
        if (!StringUtil.isNullOrEmpty(stuRequest.getName())) {
            criteria.andNameLike("%" + stuRequest.getName() + "%");
        }
        if (!StringUtil.isNullOrEmpty(stuRequest.getSex())){
            criteria.andSexEqualTo(stuRequest.getSex());
        }
        return stuMapper.selectByExample(stuExample);
    }

    @Transactional()
    @Override
    public int deleteStu(StuRequest stuRequest) {
        if (stuRequest.getId() != null){
           return stuMapper.deleteByPrimaryKey(stuRequest.getId());
        }
        return 0;
    }
}
