package com.yc.api;

import com.yc.api.config.FeignConfig;
import com.yc.bean.Resfood;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "res-food",path = "resfood",configuration= FeignConfig.class)
public interface ResfoodApi {


    @RequestMapping(value = "findByPage",method = {RequestMethod.GET})
    public Map<String,Object> findByPage(@RequestParam int pageno,@RequestParam int pagesize,
                                         @RequestParam String sortby,@RequestParam String sort
    );


    @GetMapping("findById/{fid}")
    public Map<String,Object>findById(@RequestParam Integer fid);

    @GetMapping("findAll")
    public  Map<String,Object> findAll();
}
