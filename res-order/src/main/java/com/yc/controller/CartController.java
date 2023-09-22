package com.yc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.api.ResfoodApi;
import com.yc.bean.Resfood;
import com.yc.model.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("cart")
public class CartController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    //服务发现类，是rest客户端
    //利用 http请求  发送  请求其他服务

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResfoodApi resfoodApi;


    @RequestMapping("clearAll")
    public Map<String,Object> clearAll(@RequestHeader("Authorization") String bearerToken){
        Map<String,Object> result = new HashMap<String,Object>();
        if(this.redisTemplate.hasKey("cart_"+bearerToken)){
            // 取出这个人所以的购物项   只删除商品部分 ，而不是清除整个购物车
            Set<Object> keys = this.redisTemplate.opsForHash().keys("cart_"+bearerToken);

            this.redisTemplate.opsForHash().delete("cart_"+bearerToken,keys.toArray());
            result.put("code",1);
            result.put("obj",keys);

        }else {
            result.put("code",0);
        }
        return result;
    }

    @RequestMapping("getCartInfo")
    public  Map<String,Object> getCartInfo(@RequestHeader("Authorization") String bearerToken){
        Map<String,Object> result = new HashMap<String,Object>();
        if(this.redisTemplate.hasKey("cart_"+bearerToken)){
           Map<Object,Object> cart = this.redisTemplate.opsForHash().entries("cart_"+bearerToken);
           log.info("token为"+bearerToken+"的购物车含有商品：: " + cart);

            result.put("code",1);
            result.put("data",cart.values());


        }else {
            result.put("code",0);
        }
        return result;
    }

    @RequestMapping("addCart")
    public  Map<String,Object> addCart(@RequestParam Integer fid,
                                       @RequestParam Integer num,
                                       @RequestHeader("Authorization") String bearerToken) {
        Map<String, Object> result = new HashMap<String, Object>();
        Resfood rf = null;
        //方案一 ：  通过地址 发送http请求，
//        String url = "http://localhost:9001/resfood/findById/" + fid;


        //方案二  利用服务名，通过服务 发现功能自动找url
//        String url ="http://res-food/resfood/findById/" + fid;
//        Map<String, Object> resultMap =  this.restTemplate.getForObject(url,Map.class);
        //方法三：  opFeign  方案

        Map<String, Object> resultMap = this.resfoodApi.findById(fid);

        if ("1".equalsIgnoreCase(resultMap.get("code").toString())) {
            Map m = (Map) resultMap.get("obj");
            ObjectMapper mapper = new ObjectMapper();

            rf = mapper.convertValue(m, Resfood.class);

        } else {
            result.put("code", 0);
            result.put("msg", "查无此商品" + fid);
            return result;
        }
        //获取购物车的信息
        CartItem ci = (CartItem) this.redisTemplate.opsForHash().get("cart_"+bearerToken,fid+"");

        if(ci == null){
         ci = new CartItem();
         ci.setFood(rf);
         ci.setNum(num);
        }else {
            int newNum = ci.getNum() +num;
            ci.setNum(newNum);
        }
        if(ci.getNum()<=0){
            this.redisTemplate.opsForHash().delete("cart_"+bearerToken,fid+"");
        }else {
            ci.getSmallCount();  //计算小计
            this.redisTemplate.opsForHash().put("cart_"+bearerToken,fid+"", ci);
        }
        result.put("code",1);
        Map  m = redisTemplate.opsForHash().entries("cart_"+bearerToken);
        result.put("data",m.values());
        return result;
    }


}
