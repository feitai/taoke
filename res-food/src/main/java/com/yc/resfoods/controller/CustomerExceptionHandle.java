package com.yc.resfoods.controller;

import org.junit.jupiter.api.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(-100000)
public class CustomerExceptionHandle {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> handleRuntimeException(RuntimeException exception){
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg",": 业务错误"+ exception.getMessage());
        return map;
    }
}
