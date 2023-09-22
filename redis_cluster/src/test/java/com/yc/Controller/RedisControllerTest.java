package com.yc.Controller;

import com.yc.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@Service
class RedisControllerTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void test1(){
        System.out.println(redisService);
    }

}