package com.yc.api;

import com.yc.api.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@FeignClient(value = "res-order" , path = "cart",configuration= FeignConfig.class)
//@Produces("application/json")
public interface CartApi {

    @RequestMapping(value ="clearAll", method = RequestMethod.GET)
//    @GET
//    @Path("clearAll")
    public Map<String, Object> clearAll(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(value ="getCartInfo", method = RequestMethod.GET)
//    @GET
//    @Path("getCartInfo")
    public Map<String, Object> getCartInfo(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(value ="addToCart", method = RequestMethod.GET)
//    @GET
//    @Path("addToCart")
    public Map<String, Object> addToCart(@RequestParam Integer fid,
                                         @RequestParam Integer num,
                                         @RequestHeader("Authorization") String bearerToken);



}
