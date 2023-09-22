package com.yc.controller;

import com.yc.api.CartApi;
import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.biz.ResorderBiz;
import com.yc.model.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ResorderBiz resorderBiz;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartApi cartApi;

    @PostMapping("orderFood")
    public Map<String,Object> orderFood(Resorder resorder, @RequestHeader("Authorization") String bearerToken) {
        Map<String, Object> map = new HashMap<>();
        if (!this.redisTemplate.hasKey("cart_" + bearerToken) || this.redisTemplate.opsForHash().entries("cart_" + bearerToken).size() < 0) {
            map.put("code", 0);
            return map;
        }

        Map<String, CartItem> cart = this.redisTemplate.opsForHash().entries("cart_" + bearerToken);
        Collection<CartItem> cis = (Collection<CartItem>) cart.values();
        //TODO:调用业务层完成订单记录操作

        Resuser user = new Resuser();

        //TODO:  到security 服务去取userid，
//        String url = "http://localhost:8001/resfood/hello";
        String url = "http://res-security/ressecurity/resfood/hello";
        HttpHeaders header = new HttpHeaders() {{
            set("Authorization", bearerToken);
            set("User-Agent","yc IE");
        }};
        ResponseEntity<Map> re = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Map>(header), Map.class);
        Map m = re.getBody();
        int userid = Integer.parseInt(m.get("userid").toString());
        user.setUserid(userid);
        //处理订单时间
        //创建Datetime对象  表示当前时间 和日期
        LocalDateTime now = LocalDateTime.now();
        //创建DatetimeFormat对象，指定时间日期格式为： yyyy-mm-dd hh:mm:ss

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //调用foremat方法  将LocalDateTime转化为字符串
        resorder.setOrdertime(formatter.format(now));
        LocalDateTime deliveryTime = now.plusHours(5);
        resorder.setDeliverytime(formatter.format(deliveryTime));
        resorder.setStatus(0);
        resorderBiz.order(resorder, new HashSet<>(cis), user);
        double total = 0;
        for (CartItem ci : cis) {
            total += ci.getSmallCount();
        }
        this.redisTemplate.delete("cart_" + bearerToken);
        map.put("code", 1);
        return map;
    }


    @GetMapping("payAction")
    public Map<String,Object> payAction (Integer flag ){
        //  TODO：测试慢调用
        if (flag == null){
//            Thread.sleep(1000);
            throw new RuntimeException("PayAction");
        }
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("code",1);
        return map;
    }
}
