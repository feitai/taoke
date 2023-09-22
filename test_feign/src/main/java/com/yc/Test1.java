package com.yc;

import com.yc.api.GitHub;
import com.yc.bean.Contributor;
import feign.Feign;
import feign.gson.GsonDecoder;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        //构建器模式
        GitHub github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor);
        }
    }
}