package com.wenhao.shop.sevice.weixin.service;

import com.wenhao.shop.entity.entity.AppEnity;
import org.springframework.web.bind.annotation.GetMapping;

public interface WeixinService {
    @GetMapping("/getApp")
    AppEnity getApp();
}
