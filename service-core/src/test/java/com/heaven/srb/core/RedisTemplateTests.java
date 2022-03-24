package com.heaven.srb.core;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaven.srb.core.mapper.DictMapper;
import com.heaven.srb.core.pojo.entity.Dict;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@SpringBootTest
public class RedisTemplateTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DictMapper dictMapper;
    @Test
    public void Redistest1(){
        Dict dict = dictMapper.selectById(1);
        redisTemplate.opsForValue().set("dict1",dict,5, TimeUnit.MINUTES);
    }

    @Test
    public void getValue(){
        Dict dict1 = (Dict)redisTemplate.opsForValue().get("dict1");
        System.out.println(dict1);
    }

    @Test
    public void getName(){
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
