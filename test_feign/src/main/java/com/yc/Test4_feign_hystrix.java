package com.yc;

import com.yc.api.GitHub;
import com.yc.api.GitHub2_javax;
import com.yc.bean.Contributor;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.hystrix.HystrixFeign;
import feign.jaxrs.JAXRSContract;
import feign.okhttp.OkHttpClient;

import java.util.List;

public class Test4_feign_hystrix {
    public static void main(String[] args) {
        GitHub github = HystrixFeign.builder()
                .target(GitHub.class, "https://api.github.com");


        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor);
        }
    }
}
