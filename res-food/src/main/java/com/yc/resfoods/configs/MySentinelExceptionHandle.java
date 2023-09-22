package com.yc.resfoods.configs;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2GoAwayFrame;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MySentinelExceptionHandle implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        String msg = null;
        if(e instanceof FlowException) {
            msg = " 访问频繁 ，请稍后再试";
        }else if(e instanceof DegradeException){
            msg = " 系统降级";
        }else if(e instanceof ParamFlowException){
            msg = "   热点参数异常 ：" + e.getMessage() + "," + ((ParamFlowException) e).getResourceName() + ","+ e.getRule();
        }else if(e instanceof SystemBlockException){
            msg = "   系统规则限流或降级 ：" + e.getMessage();
        }else if(e instanceof AuthorityException){
            msg = "   权限不足 ：" + e.getMessage();
        }else {
            msg = "未知限流降级" + e.getMessage();
        }

        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        Map map = new HashMap();
        map.put("code",0);
        map.put("msg",msg);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.flush();
    }
}
