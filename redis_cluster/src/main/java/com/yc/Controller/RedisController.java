package com.yc.Controller;

import com.yc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/set")
    public void setValue() {
        redisService.setValue("exampleKey", "exampleValue");
    }

    @GetMapping("/get")
    public String getValue() {
        return redisService.getValue("exampleKey");
    }

    @GetMapping("/hello")
    public String Hello() {
        return "Hello";
    }
}
