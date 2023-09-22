package com.yc.api;

import com.yc.bean.Contributor;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface GitHub {
    //   TODO: 和requestMapping是一样的
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
    // @RequestLine  @Param  => Contract 合同  地址映射的规范   是feign提供的
}
