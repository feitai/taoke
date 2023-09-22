package com.yc.resfoods.configs;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class CustomerRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        log.info("解析Origin的请求的来源是: " + httpServletRequest.getHeader(("source")));
        return httpServletRequest.getHeader(("source"));

    }
}
