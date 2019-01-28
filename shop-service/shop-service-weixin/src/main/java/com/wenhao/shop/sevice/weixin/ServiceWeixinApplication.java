package com.wenhao.shop.sevice.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceWeixinApplication.class, args);
    }
}
